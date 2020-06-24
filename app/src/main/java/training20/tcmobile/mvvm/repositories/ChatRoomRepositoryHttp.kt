package training20.tcmobile.mvvm.repositories

import training20.tcmobile.net.http.HttpClient
import training20.tcmobile.net.http.HttpMethod
import training20.tcmobile.net.http.responses.ChatMessageResponse
import training20.tcmobile.net.http.responses.ChatRoomHistoryHairdresserResponse
import training20.tcmobile.net.http.responses.ChatRoomHistoryModelResponse
import training20.tcmobile.net.http.responses.ErrorResponse
import java.io.IOException

class ChatRoomRepositoryHttp: ChatRoomRepositoryContract {

    override fun messages(
        chatRoomId: Int,
        onSuccess: ((Array<ChatMessageResponse>) -> Unit)?,
        onError: ((String, Int, ErrorResponse) -> Unit)?,
        onFailure: ((IOException) -> Unit)?,
        onComplete: (() -> Unit)?
    ) {
        HttpClient(Array<ChatMessageResponse>::class.java, HttpMethod.GET, "chat_rooms/${chatRoomId}/messages")
            .send(onSuccess, onError, onFailure, onComplete)
    }

    override fun historyHairdresser(
        onSuccess: ((Array<ChatRoomHistoryHairdresserResponse>) -> Unit)?,
        onError: ((String, Int, ErrorResponse) -> Unit)?,
        onFailure: ((IOException) -> Unit)?,
        onComplete: (() -> Unit)?
    ) {
        HttpClient(Array<ChatRoomHistoryHairdresserResponse>::class.java, HttpMethod.GET, "chat_rooms/history")
            .send(onSuccess, onError, onFailure, onComplete)
    }

    override fun historyModel(
        onSuccess: ((Array<ChatRoomHistoryModelResponse>) -> Unit)?,
        onError: ((String, Int, ErrorResponse) -> Unit)?,
        onFailure: ((IOException) -> Unit)?,
        onComplete: (() -> Unit)?
    ) {
        HttpClient(Array<ChatRoomHistoryModelResponse>::class.java, HttpMethod.GET, "chat_rooms/history")
            .send(onSuccess, onError, onFailure, onComplete)
    }
}