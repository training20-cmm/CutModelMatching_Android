package training20.tcmobile.repositories

import training20.tcmobile.net.http.HttpClient
import training20.tcmobile.net.http.HttpMethod
import training20.tcmobile.net.http.responses.ChatRoomHistoryHairdresserResponse
import training20.tcmobile.net.http.responses.ErrorResponse
import java.io.IOException

class ChatRoomRepository {

    fun historyHairdresser(
        onSuccess: ((Array<ChatRoomHistoryHairdresserResponse>) -> Unit)? = null,
        onError: ((String, Int, ErrorResponse) -> Unit)? = null,
        onFailure: ((IOException) -> Unit)? = null,
        onComplete: (() -> Unit)? = null
    ) {
        HttpClient(Array<ChatRoomHistoryHairdresserResponse>::class.java, HttpMethod.GET, "chat_rooms/history")
            .send(onSuccess, onError, onFailure, onComplete)
    }
}