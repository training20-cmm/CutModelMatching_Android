package training20.tcmobile.mvvm.repositories

import training20.tcmobile.mvvm.models.Menu
import training20.tcmobile.net.http.HttpClient
import training20.tcmobile.net.http.HttpMethod
import training20.tcmobile.net.http.responses.ErrorResponse
import training20.tcmobile.net.http.responses.ModelRegistrationResponse
import java.io.IOException

class MenuRepositoryHttp: MenuRepositoryContract {

    // TODO: パラメータ追加(鎌田)
    // db見て書くところ
    override fun store(
        title: String,
        details: String,
        timeDates: Array<String>,
        gender: String,
        price: String,
        minutes: String,
        hairdresser_id: Int,
        onSuccess: (() -> Unit)?,
        onError: ((String, Int, ErrorResponse) -> Unit)?,
        onFailure: ((IOException) -> Unit)?,
        onComplete: (() -> Unit)?
    ) {
        val queries = mutableListOf(
            Pair("title", title),
            Pair("details", details),
            Pair("gender", gender.toString()),
            Pair("price", price),
            Pair("minutes", minutes.toString()),
            Pair("hairdresser_id", hairdresser_id.toString())
        )
        timeDates.forEach { queries.add(Pair("timeDates[]", it)) }
        HttpClient(ModelRegistrationResponse::class.java, HttpMethod.POST, "menus", *queries.toTypedArray())
            .send({
                onSuccess?.invoke()
            }, onError, onFailure, onComplete)
    }

//    override fun store(
//        title: String,
//        details: String,
//        timeDates: Array<String>,
//        gender: Char,
//        price: Int,
//        minutes: Int,
//        hairdresser_id: Int,
//        onSuccess: (() -> Unit)?,
//        onError: ((String, Int, ErrorResponse) -> Unit)?,
//        onFailure: ((IOException) -> Unit)?,
//        onComplete: (() -> Unit)?
//    ) {
//        TODO("Not yet implemented")
//    }
}