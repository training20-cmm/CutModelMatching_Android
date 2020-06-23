package training20.tcmobile.mvvm.repositories

import training20.tcmobile.mvvm.models.AccessToken
import training20.tcmobile.mvvm.models.Model
import training20.tcmobile.mvvm.models.RefreshToken
import training20.tcmobile.net.http.HttpClient
import training20.tcmobile.net.http.HttpMethod
import training20.tcmobile.net.http.RequestOptions
import training20.tcmobile.net.http.responses.ErrorResponse
import training20.tcmobile.net.http.responses.ModelRegistrationResponse
import training20.tcmobile.net.http.responses.ModelResponse
import training20.tcmobile.util.applyNotNull
import java.io.IOException

class ModelRepositoryHttp: ModelRepositoryContract {

    override fun me(
        onSuccess: ((Model?) -> Unit)?,
        onError: ((String, Int, ErrorResponse) -> Unit)?,
        onFailure: ((IOException) -> Unit)?,
        onComplete: (() -> Unit)?,
        requestOptions: RequestOptions?
    ) {

        HttpClient(ModelResponse::class.java, HttpMethod.GET, "models/me", requestOptions)
            .send({
                val model = Model(it.identifier, it.password, it.email, it.typeId, it.name, it.bioText, it.gender, it.birthday, it.userId, it.deletedAt, it.createdAt, it.updatedAt)
                onSuccess?.invoke(model)
            }, onError, onFailure, onComplete)
    }

    override fun register(
        identifier: String,
        rawPassword: String,
        rawPasswordConfirmation: String,
        name: String,
        gender: String,
        birthday: String,
        onSuccess: ((Model?, AccessToken?, RefreshToken?) -> Unit)? ,
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
        HttpClient(ModelRegistrationResponse::class.java, HttpMethod.POST, "models/register", null, *queries)
            .send({ response ->
                val model = applyNotNull(response.model?.identifier, response.model?.name, response.model?.createdAt, response.model?.updatedAt) { identifier, name, email, createdAt ->
                    Model(
                        null,
                        identifier,
                        name,
                        null,
                        null,
                        email,
                        createdAt,
                        null
                    )
                }
                val accessToken = applyNotNull(response.accessToken?.token, response.accessToken?.expiration, response.accessToken?.createdAt, response.accessToken?.updatedAt) { token, expiration, createdAt, updatedAt ->
                    AccessToken(token, expiration, createdAt, updatedAt)
                }
                val refreshToken = applyNotNull(response.refreshToken?.token, response.refreshToken?.expiration, response.refreshToken?.createdAt, response.refreshToken?.updatedAt) { token, expiration, createdAt, updatedAt ->
                    RefreshToken(token, expiration, createdAt, updatedAt)
                }
                onSuccess?.invoke(model, accessToken, refreshToken)
            }, onError, onFailure, onComplete)
    }
}