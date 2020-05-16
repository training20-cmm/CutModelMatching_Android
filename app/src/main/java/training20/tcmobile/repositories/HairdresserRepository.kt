package training20.tcmobile.repositories

import training20.tcmobile.models.hairdresser.HairdresserIdentifier
import training20.tcmobile.models.hairdresser.HairdresserName
import training20.tcmobile.models.hairdresser.HairdresserRawPassword
import training20.tcmobile.net.http.responses.ErrorResponse
import training20.tcmobile.net.http.responses.HairdresserRegistrationResponse
import java.io.IOException

interface HairdresserRepository {
    fun register(
        name: HairdresserName,
        identifier: HairdresserIdentifier,
        rawPassword: HairdresserRawPassword,
        rawPasswordConfirmation: HairdresserRawPassword,
        onSuccess: ((HairdresserRegistrationResponse) -> Unit)? = null,
        onError: ((String, Int, ErrorResponse) -> Unit)? = null,
        onFailure: ((IOException) -> Unit)? = null,
        onComplete: (() -> Unit)? = null
    )
}