package training20.tcmobile.mvvm.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import org.java_websocket.client.WebSocketClient
import org.java_websocket.handshake.ServerHandshake
import org.json.JSONObject
import training20.tcmobile.ApplicationCMM
import training20.tcmobile.auth.AuthManager
import training20.tcmobile.mvvm.actions.HairdresserChatRoomActions
import training20.tcmobile.mvvm.event.EventDispatcher
import training20.tcmobile.mvvm.models.ChatMessage
import training20.tcmobile.mvvm.repositories.ChatRoomRepositoryContract
import training20.tcmobile.net.http.responses.ChatMessageResponse
import java.net.URI

class HairdresserChatRoomViewModel(
    eventDispatcher: EventDispatcher<HairdresserChatRoomActions>,
    private val chatRoomRepository: ChatRoomRepositoryContract,
    private val authManager: AuthManager
): BackableViewModel<HairdresserChatRoomActions>(eventDispatcher) {

    private inner class WSClient: WebSocketClient(URI(ApplicationCMM.wsServerOrigin)) {
        override fun onOpen(handshakedata: ServerHandshake?) {
            val hairdresser = authManager.currentHairdresser()
            hairdresser?.let {
                val jsonObject = JSONObject()
                jsonObject.put("myUserId", hairdresser.userId)
                jsonObject.put("partnerUserId", partnerUserId)
                webSocketClient.send(jsonObject.toString())
            }
        }

        override fun onClose(code: Int, reason: String?, remote: Boolean) {
            println(code)
        }

        override fun onMessage(message: String?) {
            message?.let {
                appendChatMessage(message)
            }
        }

        override fun onError(ex: Exception?) {
            println(ex)
        }
    }

    val chatMessages: LiveData<MutableList<ChatMessage>>
        get() = _chatMessages
    private val _chatMessages = MutableLiveData<MutableList<ChatMessage>>()
    private val webSocketClient = WSClient()
    private var partnerUserId: Int? = null

    fun start(chatRoomId: Int, partnerUserId: Int) {
        this.partnerUserId = partnerUserId
        chatRoomRepository.messages(
            chatRoomId,
            this::onMessageSuccess
        )
        webSocketClient.connect()
    }

    fun sendMessage(message: String) {
        appendChatMessage(message)
        webSocketClient.send(message)
    }

    private fun appendChatMessage(message: String) {
        _chatMessages.value?.add(ChatMessage(content = message))
        eventDispatcher.dispatchEvent { onMessageReceived(message) }
    }

    private fun onMessageSuccess(response: Array<ChatMessageResponse>) {
        _chatMessages.value = response.map {
            ChatMessage(
                content = it.content
            )
        }.toMutableList()
        eventDispatcher.dispatchEvent { onChatMessagesChanged() }
    }
}