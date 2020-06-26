package training20.tcmobile.mvvm.repositories

import training20.tcmobile.mvvm.models.AccessToken
import training20.tcmobile.mvvm.models.Model
import training20.tcmobile.mvvm.models.RefreshToken
import training20.tcmobile.net.http.RequestOptions
import training20.tcmobile.net.http.responses.ErrorResponse
import training20.tcmobile.net.http.responses.ModelRegistrationResponse
import java.io.IOException

interface ModelRepositoryContract {

    fun me(
        onSuccess: ((Model?) -> Unit)? = null,
        onError: ((String, Int, ErrorResponse) -> Unit)? = null,
        onFailure: ((IOException) -> Unit)? = null,
        onComplete: (() -> Unit)? = null,
        requestOptions: RequestOptions? = null
    )

    fun register(
        identifier: String,
        rawPassword: String,
        rawPasswordConfirmation: String,
        name: String,
        gender: String,
        birthday: String,
        onSuccess: ((Model?, AccessToken?, RefreshToken?) -> Unit)? = null,
        onError: ((String, Int, ErrorResponse) -> Unit)? = null,
        onFailure: ((IOException) -> Unit)? = null,
        onComplete: (() -> Unit)? = null
    )
}