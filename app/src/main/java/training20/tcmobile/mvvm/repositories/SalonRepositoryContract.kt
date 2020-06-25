package training20.tcmobile.mvvm.repositories

import training20.tcmobile.net.http.RequestOptions
import training20.tcmobile.net.http.responses.ErrorResponse
import training20.tcmobile.net.http.responses.ModelRegistrationResponse
import training20.tcmobile.net.http.responses.SalonResponse
import java.io.IOException

interface SalonRepositoryContract {

//    "name",
//    "postcode",
//    "prefecture",
//    "address",
//    "building",
//    "bio_text",
//    "capacity",
//    "parking",
//    "open_hours_weekdays",
//    "close_hours_weekdays",
//    "open_hours_weekends",
//    "close_hours_weekends",
//    "regular_holiday",

//    fun store(
//        identifier: String,
//        rawPassword: String,
//        rawPasswordConfirmation: String,
//        name: String,
//        gender: String,
//        birthday: String,
//        onSuccess: ((ModelRegistrationResponse) -> Unit)?,
//        onError: ((String, Int, ErrorResponse) -> Unit)?,
//        onFailure: ((IOException) -> Unit)?,
//        onComplete: (() -> Unit)?
//    ) {
//    }

    fun index(
        onSuccess: ((SalonResponse) -> Unit)? = null,
        onError: ((String, Int, ErrorResponse) -> Unit)? = null,
        onFailure: ((IOException) -> Unit)? = null,
        onComplete: (() -> Unit)? = null,
        requestOptions: RequestOptions? = null
    )
}