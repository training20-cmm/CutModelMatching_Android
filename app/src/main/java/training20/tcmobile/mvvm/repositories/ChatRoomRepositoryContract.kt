package training20.tcmobile.mvvm.repositories

import training20.tcmobile.net.http.responses.ChatMessageResponse
import training20.tcmobile.net.http.responses.ChatRoomHistoryHairdresserResponse
import training20.tcmobile.net.http.responses.ChatRoomHistoryModelResponse
import training20.tcmobile.net.http.responses.ErrorResponse
import java.io.IOException

interface ChatRoomRepositoryContract {

    fun messages(
        chatRoomId: Int,
        onSuccess: ((Array<ChatMessageResponse>) -> Unit)? = null,
        onError: ((String, Int, ErrorResponse) -> Unit)? = null,
        onFailure: ((IOException) -> Unit)? = null,
        onComplete: (() -> Unit)? = null
    )

    fun historyHairdresser(
        onSuccess: ((Array<ChatRoomHistoryHairdresserResponse>) -> Unit)? = null,
        onError: ((String, Int, ErrorResponse) -> Unit)? = null,
        onFailure: ((IOException) -> Unit)? = null,
        onComplete: (() -> Unit)? = null
    )

    fun historyModel(
        onSuccess: ((Array<ChatRoomHistoryModelResponse>) -> Unit)? = null,
        onError: ((String, Int, ErrorResponse) -> Unit)? = null,
        onFailure: ((IOException) -> Unit)? = null,
        onComplete: (() -> Unit)? = null
    )
}