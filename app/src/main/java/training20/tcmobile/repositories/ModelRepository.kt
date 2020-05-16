package training20.tcmobile.repositories

import training20.tcmobile.models.model.*
import training20.tcmobile.net.http.responses.ErrorResponse
import training20.tcmobile.net.http.responses.ModelRegistrationResponse
import java.io.IOException

interface ModelRepository {
    fun register(
        name: ModelName,
        id: ModelIdentifier,
        rawPassword: ModelRawPassword,
        rawPasswordConfirmation: ModelRawPassword,
        onSuccess: ((ModelRegistrationResponse) -> Unit)? = null,
        onError: ((String, Int, ErrorResponse) -> Unit)? = null,
        onFailure: ((IOException) -> Unit)? = null,
        onComplete: (() -> Unit)? = null
    )
}