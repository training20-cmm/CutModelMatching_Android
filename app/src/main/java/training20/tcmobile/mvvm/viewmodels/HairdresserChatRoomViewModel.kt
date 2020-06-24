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
import training20.tcmobile.util.ensureNotNull
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
            val hairdresser = authManager.currentHairdresser()
            ensureNotNull(hairdresser, message) { hairdresser, message ->
                val jsonObject = JSONObject(message)
                val content = jsonObject.get("text") as? String
                val createdAt = jsonObject.get("createdAt") as? String
                val userId = jsonObject.get("myUserId") as? Int
                val isIncoming =  hairdresser.userId != userId
                _chatMessages.value?.add(ChatMessage(
                    content = content,
                    createdAt = createdAt,
                    isIncoming = isIncoming
                ))
                eventDispatcher.dispatchEvent { onMessageReceived(message, isIncoming) }
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
    private var chatRoomId: Int? = null
    private var partnerUserId: Int? = null
    private val hairdresser = authManager.currentHairdresser()

    fun start(chatRoomId: Int, partnerUserId: Int) {
        this.chatRoomId = chatRoomId
        this.partnerUserId = partnerUserId
        chatRoomRepository.messages(
            chatRoomId,
            this::onMessagesSuccess
        )
        webSocketClient.connect()
    }

    fun sendMessage(message: String, myUserId: Int, partnerUserId: Int) {
        val jsonObject = JSONObject()
        jsonObject.put("text", message)
        jsonObject.put("chatRoomId", chatRoomId)
        jsonObject.put("myUserId", myUserId)
        jsonObject.put("partnerUserId", partnerUserId)
        webSocketClient.send(jsonObject.toString())
    }

    private fun onMessagesSuccess(response: Array<ChatMessageResponse>) {
        hairdresser?.let {
            _chatMessages.value = response.mapNotNull {
                applyNotNull(it.content, it.userId) { content, userId ->
                    ChatMessage(
                        content = it.content,
                        userId = it.userId,
                        createdAt = it.createdAt,
                        isIncoming = hairdresser.userId != userId
                    )
                }
            }.toMutableList()
            eventDispatcher.dispatchEvent { onMessagesChanged() }
        }
    }
}