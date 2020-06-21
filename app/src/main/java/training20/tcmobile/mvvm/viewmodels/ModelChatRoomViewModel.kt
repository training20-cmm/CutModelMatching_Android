package training20.tcmobile.mvvm.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import org.java_websocket.client.WebSocketClient
import org.java_websocket.handshake.ServerHandshake
import training20.tcmobile.ApplicationCMM
import training20.tcmobile.mvvm.actions.ModelChatRoomActions
import training20.tcmobile.mvvm.event.EventDispatcher
import training20.tcmobile.mvvm.models.ChatMessage
import training20.tcmobile.mvvm.repositories.ChatRoomRepositoryContract
import training20.tcmobile.net.http.responses.ChatMessageResponse
import java.net.URI
//
//private inner class MyWebSocketClient(uri: URI) : WebSocketClient(uri) {
//
//
////        private val contentView: TextView by lazy {
////            activity.findViewById<TextView>(R.id.messageList)
////        }
//
//    private val breakLine = System.lineSeparator()
//
//
//    override fun onOpen(handshakedata: ServerHandshake?) {
//        Log.i(javaClass.simpleName, "WSサーバに接続しました。")
//        Log.i(javaClass.simpleName, "スレッド：「${Thread.currentThread().name}」で実行中")
//
//    }
//
//    override fun onClose(code: Int, reason: String?, remote: Boolean) {
//        Log.i(javaClass.simpleName, "WSサーバから切断しました。reason:${reason}")
//        Log.i(javaClass.simpleName, "スレッド：「${Thread.currentThread().name}」で実行中")
//    }
//
//    override fun onMessage(message: String?) {
//        Log.i(javaClass.simpleName, "メッセージを受け取りました。")
//        Log.i(javaClass.simpleName, "スレッド：「${Thread.currentThread().name}」で実行中")
//        runOnUiThread {
////                contentView.append("$message")
////                contentView.append("$breakLine")
//            recyclerViewAdapter?.addMessage(message!!)
//            Log.i(javaClass.simpleName, "メッセージをTextViewに追記しました。")
//            Log.i(javaClass.simpleName, "スレッド：「${Thread.currentThread().name}」で実行中")
//
//        }
//    }
//
//    override fun onError(ex: Exception?) {
//        Log.i(javaClass.simpleName, "エラーが発生しました。", ex)
//        Log.i(javaClass.simpleName, "スレッド：「${Thread.currentThread().name}」で実行中")
//    }
//}

class ModelChatRoomViewModel(
    eventDispatcher: EventDispatcher<ModelChatRoomActions>,
    private val chatRoomRepository: ChatRoomRepositoryContract
) : BackableViewModel<ModelChatRoomActions>(eventDispatcher) {

    private inner class WSClient: WebSocketClient(URI(ApplicationCMM.wsServerOrigin)) {
        override fun onOpen(handshakedata: ServerHandshake?) {
        }

        override fun onClose(code: Int, reason: String?, remote: Boolean) {
        }

        override fun onMessage(message: String?) {
            message?.let {
                appendChatMessage(message)
            }
        }

        override fun onError(ex: Exception?) {
        }
    }

    val chatMessages: LiveData<MutableList<ChatMessage>>
        get() = _chatMessages

    private val _chatMessages = MutableLiveData<MutableList<ChatMessage>>()

    private val webSocketClient = WSClient()

    fun start(chatRoomId: Int) {
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