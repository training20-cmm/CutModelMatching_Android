package training20.tcmobile.mvvm.repositories

import training20.tcmobile.net.http.HttpClient
import training20.tcmobile.net.http.HttpMethod
import training20.tcmobile.net.http.responses.ErrorResponse
import training20.tcmobile.net.http.responses.ModelRegistrationResponse
import java.io.IOException

class ModelRepositoryHttp: ModelRepositoryContract {

    override fun register(
        identifier: String,
        rawPassword: String,
        rawPasswordConfirmation: String,
        name: String,
        gender: String,
        birthday: String,
        onSuccess: ((ModelRegistrationResponse) -> Unit)? ,
        onError: ((String, Int, ErrorResponse) -> Unit)?,
        onFailure: ((IOException) -> Unit)? ,
        onComplete: (() -> Unit)?
    ) {
        val queries = arrayOf(
            Pair("identifier", identifier),
            Pair("password", rawPassword),
            Pair("password_confirmation", rawPasswordConfirmation),
            Pair("name", name),
            Pair("gender", gender),
            Pair("birthday", birthday)
        )
        HttpClient(ModelRegistrationResponse::class.java, HttpMethod.POST, "models/register", *queries)
            .send(onSuccess, onError, onFailure, onComplete)
    }
}