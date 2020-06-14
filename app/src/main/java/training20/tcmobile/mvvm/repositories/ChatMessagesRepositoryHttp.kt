package training20.tcmobile.mvvm.repositories

import training20.tcmobile.net.http.HttpClient
import training20.tcmobile.net.http.HttpMethod
import training20.tcmobile.net.http.responses.ChatMessageResponse
import training20.tcmobile.net.http.responses.ErrorResponse
import java.io.IOException

class ChatMessagesRepositoryHttp: ChatMessageRepositoryContract {

    override fun index(
        onSuccess: ((Array<ChatMessageResponse>) -> Unit)? ,
        onError: ((String, Int, ErrorResponse) -> Unit)?,
        onFailure: ((IOException) -> Unit)?,
        onComplete: (() -> Unit)?
    ) {
        HttpClient(Array<ChatMessageResponse>::class.java, HttpMethod.GET, "chat_messages")
            .send(onSuccess, onError, onFailure, onComplete)
    }
}