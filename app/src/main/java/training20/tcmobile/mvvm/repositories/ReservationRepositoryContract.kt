package training20.tcmobile.mvvm.repositories

import training20.tcmobile.mvvm.models.Model
import training20.tcmobile.mvvm.models.Reservation
import training20.tcmobile.net.http.RequestOptions
import training20.tcmobile.net.http.responses.ErrorResponse
import java.io.IOException

interface ReservationRepositoryContract {

    fun index(
        onSuccess: ((Array<Reservation>) -> Unit)? = null,
        onError: ((String, Int, ErrorResponse) -> Unit)? = null,
        onFailure: ((IOException) -> Unit)? = null,
        onComplete: (() -> Unit)? = null,
        requestOptions: RequestOptions? = null
    )

    fun store(
        menuId: Int,
        menuTimeId: Int,
        onSuccess: ((Reservation) -> Unit)? = null,
        onError: ((String, Int, ErrorResponse) -> Unit)? = null,
        onFailure: ((IOException) -> Unit)? = null,
        onComplete: (() -> Unit)? = null
    )
}