package training20.tcmobile.repositories

import training20.tcmobile.net.http.HttpClient
import training20.tcmobile.net.http.HttpMethod
import training20.tcmobile.net.http.RequestOptions
import training20.tcmobile.net.http.ResponseHandler
import training20.tcmobile.net.http.responses.ChatMessageResponse
import training20.tcmobile.net.http.responses.ErrorResponse
import java.io.IOException

class ChatMessagesRepository {

    fun index(
        onSuccess: ((Array<ChatMessageResponse>) -> Unit)? = null,
        onError: ((String, Int, ErrorResponse) -> Unit)? = null,
        onFailure: ((IOException) -> Unit)? = null,
        onComplete: (() -> Unit)? = null
    ) {
        HttpClient(Array<ChatMessageResponse>::class.java, HttpMethod.GET, "chat_messages")
            .send(onSuccess, onError, onFailure, onComplete)
    }
}