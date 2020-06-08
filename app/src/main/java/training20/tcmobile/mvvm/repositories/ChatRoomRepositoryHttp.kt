package training20.tcmobile.mvvm.repositories

import training20.tcmobile.net.http.HttpClient
import training20.tcmobile.net.http.HttpMethod
import training20.tcmobile.net.http.responses.ChatRoomHistoryHairdresserResponse
import training20.tcmobile.net.http.responses.ErrorResponse
import java.io.IOException

class ChatRoomRepositoryHttp: ChatRoomRepositoryContract {

    override fun historyHairdresser(
        onSuccess: ((Array<ChatRoomHistoryHairdresserResponse>) -> Unit)?,
        onError: ((String, Int, ErrorResponse) -> Unit)?,
        onFailure: ((IOException) -> Unit)?,
        onComplete: (() -> Unit)?
    ) {
        HttpClient(Array<ChatRoomHistoryHairdresserResponse>::class.java, HttpMethod.GET, "chat_rooms/history")
            .send(onSuccess, onError, onFailure, onComplete)
    }
}