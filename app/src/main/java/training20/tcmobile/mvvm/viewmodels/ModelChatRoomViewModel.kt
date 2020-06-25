package training20.tcmobile.mvvm.viewmodels

import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.android.synthetic.main.fragment_model_chat_room.*
import org.java_websocket.client.WebSocketClient
import org.java_websocket.handshake.ServerHandshake
import org.json.JSONObject
import training20.tcmobile.ApplicationCMM
import training20.tcmobile.auth.AuthManager
import training20.tcmobile.mvvm.actions.ModelChatRoomActions
import training20.tcmobile.mvvm.event.EventDispatcher
import training20.tcmobile.mvvm.models.ChatMessage
import training20.tcmobile.mvvm.repositories.ChatRoomRepositoryContract
import training20.tcmobile.net.http.responses.ChatMessageResponse
import training20.tcmobile.util.applyNotNull
import training20.tcmobile.util.ensureNotNull
import java.net.URI
import java.util.concurrent.Executor

class ModelChatRoomViewModel(
    eventDispatcher: EventDispatcher<ModelChatRoomActions>,
    private val chatRoomRepository: ChatRoomRepositoryContract,
    private val authManager: AuthManager
) : BackableViewModel<ModelChatRoomActions>(eventDispatcher) {

    private inner class WSClient: WebSocketClient(URI(ApplicationCMM.wsServerOrigin)) {
        override fun onOpen(handshakedata: ServerHandshake?) {
            val model = authManager.currentModel()
            model?.let {
                val jsonObject = JSONObject()
                jsonObject.put("myUserId", model.userId)
                jsonObject.put("partnerUserId", partnerUserId)
                webSocketClient.send(jsonObject.toString())
            }
        }

        override fun onClose(code: Int, reason: String?, remote: Boolean) {
            println(code)
        }

        override fun onMessage(message: String?) {
            val model = authManager.currentModel()
            ensureNotNull(model, message) { model, message ->
                val jsonObject = JSONObject(message)
                val content = jsonObject.get("text") as? String
                val createdAt = jsonObject.get("createdAt") as? String
                val userId = jsonObject.get("myUserId") as? Int
                val isIncoming =  model.userId != userId
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
    private val model = authManager.currentModel()
    private var chatRoomId: Int? = null
    private var partnerUserId: Int? = null

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
        model?.let {
            _chatMessages.value = response.mapNotNull {
                applyNotNull(it.content, it.userId) { content, userId ->
                    ChatMessage(
                        content = it.content,
                        createdAt = it.createdAt,
                        isIncoming = model.userId != userId
                    )
                }
            }.toMutableList()
            eventDispatcher.dispatchEvent { onMessagesChanged() }
        }
    }
}