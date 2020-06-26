package training20.tcmobile.mvvm.repositories

import training20.tcmobile.mvvm.models.Salon
import training20.tcmobile.net.http.HttpClient
import training20.tcmobile.net.http.HttpMethod
import training20.tcmobile.net.http.RequestOptions
import training20.tcmobile.net.http.responses.ErrorResponse
import training20.tcmobile.net.http.responses.SalonResponse
import training20.tcmobile.util.applyNotNull
import java.io.IOException

class SalonRepositoryHttp: SalonRepositoryContract {

    override fun index(
        onSuccess: ((Salon) -> Unit)?,
        onError: ((String, Int, ErrorResponse) -> Unit)?,
        onFailure: ((IOException) -> Unit)?,
        onComplete: (() -> Unit)?,
        requestOptions: RequestOptions?
    ) {
        HttpClient(SalonResponse::class.java, HttpMethod.GET, "salons", requestOptions)
            .send({ 
                val salon = Salon(
                    it.id,
                    it.name,
                    it.postcode,
                    it.prefecture,
                    it.address,
                    it.building,
                    it.bioText,
                    it.capacity,

                    it.openHoursWeekdays,
                    it.closeHoursWeekdays,
                    it.openHoursWeekends,
                    it.closeHoursWeekends,

                    it.regularHoliday,

                    it.images,
                    it.paymentMethods
                )
                onSuccess?.invoke(salon)
            }, onError, onFailure, onComplete)
    }
}