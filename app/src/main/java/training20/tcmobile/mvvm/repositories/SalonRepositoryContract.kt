package training20.tcmobile.mvvm.repositories

import training20.tcmobile.mvvm.models.Salon
import training20.tcmobile.net.http.RequestOptions
import training20.tcmobile.net.http.responses.ErrorResponse
import training20.tcmobile.net.http.responses.ModelRegistrationResponse
import training20.tcmobile.net.http.responses.SalonResponse
import java.io.IOException

interface SalonRepositoryContract {

    fun index(
        onSuccess: ((Salon) -> Unit)? = null,
        onError: ((String, Int, ErrorResponse) -> Unit)? = null,
        onFailure: ((IOException) -> Unit)? = null,
        onComplete: (() -> Unit)? = null,
        requestOptions: RequestOptions? = null
    )

    fun store(
        salonname: String,
        postalcode: String,
        prefecture: String,
        address: String,
        residence: String,
        salonmemo: String,
        seatsnumber: String,
        paymentMethods: MutableList<String>,
        uri: String,
        starttime: String,
        endtime: String,
        Starttime2: String,
        endtime2: String,
        regularHoliday: String,
        onSuccess: ((Salon) -> Unit)? = null,
        onError: ((String, Int, ErrorResponse) -> Unit)? = null,
        onFailure: ((IOException) -> Unit)? = null,
        onComplete: (() -> Unit)? = null
    )
}