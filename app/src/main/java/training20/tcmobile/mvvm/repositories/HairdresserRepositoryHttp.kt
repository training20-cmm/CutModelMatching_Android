package training20.tcmobile.mvvm.repositories

import training20.tcmobile.mvvm.models.Hairdresser
import training20.tcmobile.mvvm.models.Model
import training20.tcmobile.net.http.HttpClient
import training20.tcmobile.net.http.HttpMethod
import training20.tcmobile.net.http.RequestOptions
import training20.tcmobile.net.http.responses.ErrorResponse
import training20.tcmobile.net.http.responses.HairdresserRegistrationResponse
import training20.tcmobile.net.http.responses.HairdresserResponse
import training20.tcmobile.net.http.responses.ModelResponse
import java.io.IOException

class HairdresserRepositoryHttp: HairdresserRepositoryContract {

    override fun me(
        onSuccess: ((Hairdresser?) -> Unit)?,
        onError: ((String, Int, ErrorResponse) -> Unit)?,
        onFailure: ((IOException) -> Unit)?,
        onComplete: (() -> Unit)?,
        requestOptions: RequestOptions?
    ) {
        HttpClient(HairdresserResponse::class.java, HttpMethod.GET, "hairdressers/me", requestOptions)
            .send({
                val hairdresser = Hairdresser(it.id, it.identifier, it.password, it.email, it.name, it.ruby, it.bioText, it.speciality, it.profileImagePath, it.gender, it.birthday, it.years, it.salonId, it.userId, it.positionId, it.deletedAt, it.createdAt, it.updatedAt)
                onSuccess?.invoke(hairdresser)
            }, onError, onFailure, onComplete)
    }

    override fun register(
        identifier: String,
        rawPassword: String,
        rawPasswordConfirmation: String,
        name: String,
        gender: String,
        birthday: String,
        onSuccess: ((HairdresserRegistrationResponse) -> Unit)?,
        onError: ((String, Int, ErrorResponse) -> Unit)?,
        onFailure: ((IOException) -> Unit)?,
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
        HttpClient(HairdresserRegistrationResponse::class.java, HttpMethod.POST, "hairdressers/register", null, null, *queries)
            .send(onSuccess, onError, onFailure, onComplete)
    }
}