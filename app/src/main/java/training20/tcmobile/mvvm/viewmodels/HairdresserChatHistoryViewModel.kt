package training20.tcmobile.mvvm.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import training20.tcmobile.mvvm.actions.HairdresserChatHistoryActions
import training20.tcmobile.mvvm.event.EventDispatcher
import training20.tcmobile.mvvm.models.ChatMessage
import training20.tcmobile.mvvm.models.HairdresserChatRoom
import training20.tcmobile.mvvm.models.Model
import training20.tcmobile.mvvm.repositories.ChatRoomRepositoryContract
import training20.tcmobile.net.http.responses.ChatRoomHistoryHairdresserResponse
import training20.tcmobile.util.applyNotNull

class HairdresserChatHistoryViewModel(
    val eventDispatcher: EventDispatcher<HairdresserChatHistoryActions>,
    private val chatRoomRepository: ChatRoomRepositoryContract
): ViewModel() {

    val chatRooms: LiveData<List<HairdresserChatRoom>>
        get() = _chatRooms

    private val _chatRooms = MutableLiveData<List<HairdresserChatRoom>>()

    fun start() {
        chatRoomRepository.historyHairdresser(this::onHistoryHairdresserSuccess)
    }

    private fun onHistoryHairdresserSuccess(chatRooms: Array<ChatRoomHistoryHairdresserResponse>) {
        _chatRooms.value = chatRooms.mapNotNull {
            applyNotNull(it.model, it.chatMessage) { modelResponse, chatMessageResponse ->
                applyNotNull(
                    modelResponse.name,
                    chatMessageResponse.content,
                    chatMessageResponse.createdAt
                ) { name, content, createdAt ->
                    val model = Model(name = name)
                    val chatMessage = ChatMessage(content = content, createdAt= createdAt)
                    HairdresserChatRoom(model, mutableListOf(chatMessage))
                }
            }
        }
        eventDispatcher.dispatchEvent { onChatRoomsChanged() }
    }
}