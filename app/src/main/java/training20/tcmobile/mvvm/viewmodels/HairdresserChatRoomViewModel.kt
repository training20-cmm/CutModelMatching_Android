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
import training20.tcmobile.util.applyNotNull
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
                _chatMessages.value?.add(ChatMessage(content = message, isIncoming = true))
                eventDispatcher.dispatchEvent { onMessageReceived(message) }
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
    private val hairdresser = authManager.currentHairdresser()

    fun start(chatRoomId: Int, partnerUserId: Int) {
        this.partnerUserId = partnerUserId
        chatRoomRepository.messages(
            chatRoomId,
            this::onMessageSuccess
        )
        webSocketClient.connect()
    }

    fun sendMessage(message: String, myUserId: Int, partnerUserId: Int) {
        val jsonObject = JSONObject()
        jsonObject.put("text", message)
        jsonObject.put("myUserId", myUserId)
        jsonObject.put("partnerUserId", partnerUserId)
        _chatMessages.value?.add(ChatMessage(content = message, isIncoming = false))
        eventDispatcher.dispatchEvent { onMessageSent(message) }
        webSocketClient.send(jsonObject.toString())
    }

    private fun onMessageSuccess(response: Array<ChatMessageResponse>) {
        hairdresser?.let {
            _chatMessages.value = response.mapNotNull {
                applyNotNull(it.content, it.userId) { content, userId ->
                    ChatMessage(
                        content = it.content,
                        userId = it.userId,
                        isIncoming = hairdresser.userId != userId
                    )
                }
            }.toMutableList()
            eventDispatcher.dispatchEvent { onMessagesChanged() }
        }
    }
}