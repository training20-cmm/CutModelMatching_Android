package training20.tcmobile.repositories

import training20.tcmobile.models.model.ModelIdentifier
import training20.tcmobile.models.model.ModelName
import training20.tcmobile.models.model.ModelRawPassword
import training20.tcmobile.net.http.HttpClient
import training20.tcmobile.net.http.HttpMethod
import training20.tcmobile.net.http.responses.ErrorResponse
import training20.tcmobile.net.http.responses.ModelRegistrationResponse
import java.io.IOException

class ModelRepositoryHttp: ModelRepository {

    override fun register(
        name: ModelName,
        id: ModelIdentifier,
        rawPassword: ModelRawPassword,
        rawPasswordConfirmation: ModelRawPassword,
        onSuccess: ((ModelRegistrationResponse) -> Unit)?,
        onError: ((String, Int, ErrorResponse) -> Unit)?,
        onFailure: ((IOException) -> Unit)?,
        onComplete: (() -> Unit)?
    ) {
        val queries = arrayOf(
            Pair("name", name.name),
            Pair("identifier", id.identifier),
            Pair("password", rawPassword.rawPassword),
            Pair("password_confirmation", rawPasswordConfirmation.rawPassword)
        )
        HttpClient(ModelRegistrationResponse::class.java, HttpMethod.POST, "models/register", *queries)
            .send(onSuccess, onError, onFailure, onComplete)
    }
}