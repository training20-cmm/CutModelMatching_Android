package training20.tcmobile.mvvm.repositories

import training20.tcmobile.mvvm.models.Model
import training20.tcmobile.mvvm.models.Reservation
import training20.tcmobile.net.http.HttpClient
import training20.tcmobile.net.http.HttpMethod
import training20.tcmobile.net.http.RequestOptions
import training20.tcmobile.net.http.responses.ErrorResponse
import training20.tcmobile.net.http.responses.ReservationResponse
import java.io.IOException

class ReservationRepositoryHttp: ReservationRepositoryContract {

    override fun index(
        onSuccess: ((Array<Reservation>) -> Unit)?,
        onError: ((String, Int, ErrorResponse) -> Unit)?,
        onFailure: ((IOException) -> Unit)?,
        onComplete: (() -> Unit)?,
        requestOptions: RequestOptions?
    ) {
        HttpClient(Array<ReservationResponse>::class.java, HttpMethod.GET, "reservation", requestOptions)
            .send({
                val reservationList = it.map { it.model() }.toTypedArray()
                onSuccess?.invoke(reservationList)
            }, onError, onFailure, onComplete)
    }

    override fun store(
        menuId: Int,
        menuTimeId: Int,
        onSuccess: ((Reservation) -> Unit)?,
        onError: ((String, Int, ErrorResponse) -> Unit)?,
        onFailure: ((IOException) -> Unit)?,
        onComplete: (() -> Unit)?
    ) {
        val queries = arrayOf(
            Pair("menuId", menuId.toString()),
            Pair("menuTimeId", menuTimeId.toString())
        )
        HttpClient(ReservationResponse::class.java, HttpMethod.POST, "reservation", queries = *queries)
            .send({
                onSuccess?.invoke(it.model())
            }, onError, onFailure, onComplete)
    }
}