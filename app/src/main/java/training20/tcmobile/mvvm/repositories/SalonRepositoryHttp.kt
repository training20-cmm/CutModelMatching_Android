package training20.tcmobile.mvvm.repositories

import training20.tcmobile.net.http.HttpClient
import training20.tcmobile.net.http.HttpMethod
import training20.tcmobile.net.http.RequestOptions
import training20.tcmobile.net.http.responses.ErrorResponse
import training20.tcmobile.net.http.responses.SalonResponse
import java.io.IOException

class SalonRepositoryHttp: SalonRepositoryContract {

    override fun index(
        onSuccess: ((SalonResponse) -> Unit)?,
        onError: ((String, Int, ErrorResponse) -> Unit)?,
        onFailure: ((IOException) -> Unit)?,
        onComplete: (() -> Unit)?,
        requestOptions: RequestOptions?
    ) {
        HttpClient(SalonResponse::class.java, HttpMethod.GET, "salons", requestOptions)
            .send(onSuccess, onError, onFailure, onComplete)
    }
}