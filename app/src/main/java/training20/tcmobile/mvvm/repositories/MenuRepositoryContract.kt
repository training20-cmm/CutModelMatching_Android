package training20.tcmobile.mvvm.repositories

import training20.tcmobile.mvvm.models.Menu
import training20.tcmobile.net.http.responses.ErrorResponse
import java.io.IOException

interface MenuRepositoryContract {

    fun store(
        title: String,
        details: String,
        timeDates: Array<String>,
        gender: String,
        price: String,
        minutes: String,
        hairdresser_id: Int,
        onSuccess: (() -> Unit)? = null,
        onError: ((String, Int, ErrorResponse) -> Unit)? = null,
        onFailure: ((IOException) -> Unit)? = null,
        onComplete: (() -> Unit)? = null
    )
}