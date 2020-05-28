package training20.tcmobile.repositories

import training20.tcmobile.models.hairdresser.HairdresserIdentifier
import training20.tcmobile.models.hairdresser.HairdresserName
import training20.tcmobile.models.hairdresser.HairdresserRawPassword
import training20.tcmobile.net.http.HttpClient
import training20.tcmobile.net.http.HttpMethod
import training20.tcmobile.net.http.RequestOptions
import training20.tcmobile.net.http.ResponseHandler
import training20.tcmobile.net.http.responses.ErrorResponse
import training20.tcmobile.net.http.responses.HairdresserRegistrationResponse
import java.io.IOException

class HairdresserRepository {

    fun register(
        identifier: String,
        rawPassword: String,
        rawPasswordConfirmation: String,
        name: String,
        gender: String,
        birthday: String,
        requestOptions: RequestOptions? = null,
        responseHandler: ResponseHandler<HairdresserRegistrationResponse>? = null
    ) {
        val queries = arrayOf(
            Pair("identifier", identifier),
            Pair("password", rawPassword),
            Pair("password_confirmation", rawPasswordConfirmation),
            Pair("name", name),
            Pair("gender", gender),
            Pair("birthday", birthday)
        )
        HttpClient(HairdresserRegistrationResponse::class.java, HttpMethod.POST, "hairdressers/register", requestOptions, *queries)
            .send(responseHandler)
    }
}