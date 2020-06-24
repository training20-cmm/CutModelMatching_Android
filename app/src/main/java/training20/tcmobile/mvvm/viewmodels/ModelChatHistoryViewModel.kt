package training20.tcmobile.mvvm.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import training20.tcmobile.mvvm.actions.ModelChatHistoryActions
import training20.tcmobile.mvvm.event.EventDispatcher
import training20.tcmobile.mvvm.models.*
import training20.tcmobile.mvvm.repositories.ChatRoomRepositoryContract
import training20.tcmobile.net.http.responses.ChatRoomHistoryHairdresserResponse
import training20.tcmobile.net.http.responses.ChatRoomHistoryModelResponse
import training20.tcmobile.util.applyNotNull

class ModelChatHistoryViewModel(
    val eventDispatcher: EventDispatcher<ModelChatHistoryActions>,
    private val chatRoomRepository: ChatRoomRepositoryContract
): ViewModel() {

    val chatRooms: LiveData<List<ModelChatRoom>>
        get() = _chatRooms

    private val _chatRooms = MutableLiveData<List<ModelChatRoom>>()

    fun start() {
        chatRoomRepository.historyModel(this::onHistoryModelSuccess)
    }

    private fun onHistoryModelSuccess(chatRooms: Array<ChatRoomHistoryModelResponse>) {
        _chatRooms.value = chatRooms.mapNotNull {
            applyNotNull(it.hairdresser, it.id, it.chatMessage) { hairdresserResponse, id, chatMessageResponse ->
                applyNotNull(
                    hairdresserResponse.name,
                    hairdresserResponse.profileImagePath,
                    hairdresserResponse.userId,
                    chatMessageResponse.content,
                    chatMessageResponse.createdAt
                ) { name, profileImagePath, userId, content, createdAt ->
                    val hairdresser = Hairdresser(name = name, profileImagePath = profileImagePath, userId = userId)
                    val chatMessage = ChatMessage(content = content, createdAt= createdAt)
                    ModelChatRoom(hairdresser, id, mutableListOf(chatMessage))
                }
            }
        }
        eventDispatcher.dispatchEvent { onChatRoomsChanged() }
    }
}