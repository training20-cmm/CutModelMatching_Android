package training20.tcmobile.repositories

import training20.tcmobile.models.model.*
import training20.tcmobile.net.http.HttpClient
import training20.tcmobile.net.http.HttpMethod
import training20.tcmobile.net.http.RequestOptions
import training20.tcmobile.net.http.responses.ErrorResponse
import training20.tcmobile.net.http.responses.ModelRegistrationResponse
import java.io.IOException

class ModelRepository {
    fun register(
        identifier: String,
        rawPassword: String,
        rawPasswordConfirmation: String,
        name: String,
        gender: String,
        birthday: String,
        options: RequestOptions<ModelRegistrationResponse>? = null
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
            .send(options)
    }
}