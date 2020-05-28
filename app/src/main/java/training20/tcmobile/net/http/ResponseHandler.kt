package training20.tcmobile.net.http

import training20.tcmobile.net.http.responses.ErrorResponse
import java.io.IOException

class ResponseHandler<T> {

    var onSuccess: ((T) -> Unit)? = null
    var onError: ((String, Int, ErrorResponse) -> Unit)? = null
    var onFailure: ((IOException) -> Unit)? = null
    var onComplete: (() -> Unit)? = null
}