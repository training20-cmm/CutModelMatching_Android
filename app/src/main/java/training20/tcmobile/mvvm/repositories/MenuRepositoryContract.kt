package training20.tcmobile.mvvm.repositories

import android.net.Uri
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
        imageUris: MutableList<Uri>,
        tagIds: MutableList<Int>,
        treatmentIds: MutableList<Int>,
        hairdresser_id: Int,
        onSuccess: (() -> Unit)? = null,
        onError: ((String, Int, ErrorResponse) -> Unit)? = null,
        onFailure: ((IOException) -> Unit)? = null,
        onComplete: (() -> Unit)? = null
    )

    fun search(
        prefecture: String? = null,
        treatmentIds: MutableList<Int>? = null,
        minPrice: Int? = null,
        maxPrice: Int? = null,
        date: String? = null,
        minStartTime: String? = null,
        maxStartTime: String? = null,
        gender: String? = null,
        paymentMethodIds: MutableList<Int>? = null,
        salonScale: String? = null,
        parking: Boolean? = null,
        onSuccess: ((Array<Menu>) -> Unit)? = null,
        onError: ((String, Int, ErrorResponse) -> Unit)? = null,
        onFailure: ((IOException) -> Unit)? = null,
        onComplete: (() -> Unit)? = null
    )
}