package training20.tcmobile.mvvm.repositories

import training20.tcmobile.mvvm.models.Menu
import training20.tcmobile.net.http.RequestOptions
import training20.tcmobile.net.http.responses.ErrorResponse
import java.io.IOException

interface MenuRepositoryContract {

    fun show(
        id: Int,
        onSuccess: ((Menu) -> Unit)? = null,
        onError: ((String, Int, ErrorResponse) -> Unit)? = null,
        onFailure: ((IOException) -> Unit)? = null,
        onComplete: (() -> Unit)? = null,
        requestOptions: RequestOptions? = null
    )

    fun store(
        title: String,
        details: String,
        timeDates: MutableList<String>,
        timeStart: MutableList<String>,
        gender: String,
        price: String,
        minutes: String,
        treatmentIds: MutableList<Int>,
        hairdresser_id: Int,
        onSuccess: (() -> Unit)? = null,
        onError: ((String, Int, ErrorResponse) -> Unit)? = null,
        onFailure: ((IOException) -> Unit)? = null,
        onComplete: (() -> Unit)? = null
    )
}