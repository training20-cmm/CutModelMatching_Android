package training20.tcmobile.repositories

import training20.tcmobile.models.hairdresser.HairdresserIdentifier
import training20.tcmobile.models.hairdresser.HairdresserName
import training20.tcmobile.models.hairdresser.HairdresserRawPassword
import training20.tcmobile.net.http.HttpClient
import training20.tcmobile.net.http.HttpMethod
import training20.tcmobile.net.http.responses.ErrorResponse
import training20.tcmobile.net.http.responses.HairdresserRegistrationResponse
import java.io.IOException

class HairdresserRepositoryHttp: HairdresserRepository {

    override fun register(
        name: HairdresserName,
        identifier: HairdresserIdentifier,
        rawPassword: HairdresserRawPassword,
        rawPasswordConfirmation: HairdresserRawPassword,
        onSuccess: ((HairdresserRegistrationResponse) -> Unit)?,
        onError: ((String, Int, ErrorResponse) -> Unit)?,
        onFailure: ((IOException) -> Unit)?,
        onComplete: (() -> Unit)?
    ) {
        val queries = arrayOf(
            Pair("name", name.name),
            Pair("identifier", identifier.identifier),
            Pair("password", rawPassword.rawPassword),
            Pair("password_confirmation", rawPasswordConfirmation.rawPassword)
        )
        HttpClient(HairdresserRegistrationResponse::class.java, HttpMethod.POST, "hairdressers/register", *queries)
            .send(onSuccess, onError, onFailure, onComplete)
    }
}