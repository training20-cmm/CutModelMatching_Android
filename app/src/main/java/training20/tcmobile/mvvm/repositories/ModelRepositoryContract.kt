package training20.tcmobile.mvvm.repositories

import training20.tcmobile.net.http.responses.ErrorResponse
import training20.tcmobile.net.http.responses.ModelRegistrationResponse
import java.io.IOException

interface ModelRepositoryContract {

    fun register(
        identifier: String,
        rawPassword: String,
        rawPasswordConfirmation: String,
        name: String,
        gender: String,
        birthday: String,
        onSuccess: ((ModelRegistrationResponse) -> Unit)? = null,
        onError: ((String, Int, ErrorResponse) -> Unit)? = null,
        onFailure: ((IOException) -> Unit)? = null,
        onComplete: (() -> Unit)? = null
    )
}