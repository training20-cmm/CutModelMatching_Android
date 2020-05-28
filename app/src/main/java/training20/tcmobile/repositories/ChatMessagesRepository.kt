package training20.tcmobile.repositories

import training20.tcmobile.net.http.HttpClient
import training20.tcmobile.net.http.HttpMethod
import training20.tcmobile.net.http.RequestOptions
import training20.tcmobile.net.http.ResponseHandler
import training20.tcmobile.net.http.responses.ChatMessageResponse

class ChatMessagesRepository {

    fun index(
        requestOptions: RequestOptions? = null,
        responseHandler: ResponseHandler<Array<ChatMessageResponse>>? = null
    ) {
        HttpClient(Array<ChatMessageResponse>::class.java, HttpMethod.GET, "chat_messages", requestOptions)
            .send(responseHandler)
    }
}