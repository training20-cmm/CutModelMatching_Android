package training20.tcmobile.mvvm.repositories

import training20.tcmobile.net.http.responses.ErrorResponse
import training20.tcmobile.net.http.responses.HairdresserRegistrationResponse
import java.io.IOException

interface HairdresserRepositoryContract {

    fun register(
        identifier: String,
        rawPassword: String,
        rawPasswordConfirmation: String,
        name: String,
        gender: String,
        birthday: String,
        onSuccess: ((HairdresserRegistrationResponse) -> Unit)? = null,
        onError: ((String, Int, ErrorResponse) -> Unit)? = null,
        onFailure: ((IOException) -> Unit)? = null,
        onComplete: (() -> Unit)? = null
    )
}