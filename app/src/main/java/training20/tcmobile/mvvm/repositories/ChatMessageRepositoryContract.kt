package training20.tcmobile.mvvm.repositories

import training20.tcmobile.net.http.responses.ChatMessageResponse
import training20.tcmobile.net.http.responses.ErrorResponse
import java.io.IOException

interface ChatMessageRepositoryContract {

    fun index(
        onSuccess: ((Array<ChatMessageResponse>) -> Unit)? = null,
        onError: ((String, Int, ErrorResponse) -> Unit)? = null,
        onFailure: ((IOException) -> Unit)? = null,
        onComplete: (() -> Unit)? = null
    )
}