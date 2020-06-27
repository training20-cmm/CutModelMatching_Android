package training20.tcmobile.mvvm.repositories

import training20.tcmobile.mvvm.models.*
import training20.tcmobile.net.http.HttpClient
import training20.tcmobile.net.http.HttpMethod
import training20.tcmobile.net.http.RequestOptions
import training20.tcmobile.net.http.responses.ErrorResponse
import training20.tcmobile.net.http.responses.MenuResponse
import training20.tcmobile.net.http.responses.ModelRegistrationResponse
import java.io.IOException

class MenuRepositoryHttp: MenuRepositoryContract {

    override fun show(
        id: Int,
        onSuccess: ((Menu) -> Unit)?,
        onError: ((String, Int, ErrorResponse) -> Unit)?,
        onFailure: ((IOException) -> Unit)?,
        onComplete: (() -> Unit)?,
        requestOptions: RequestOptions?
    ) {
        HttpClient(MenuResponse::class.java, HttpMethod.GET, "menus/${id}", requestOptions)
            .send({
                val hairdresser = it.hairdresser?.model()
                val menuImages = it.images?.map {
                    MenuImage(
                        it.id,
                        it.path,
                        it.menuId,
                        it.createdAt,
                        it.updatedAt
                    )
                }?.toTypedArray()
                val menuTags = it.tags?.map {
                    MenuTag(
                        it.id,
                        it.name,
                        it.color,
                        it.createdAt,
                        it.updatedAt
                    )
                }?.toTypedArray()
                val menuTime = it.time?.map {
                    MenuTime(
                        it.id,
                        it.date,
                        it.start,
                        it.menuId,
                        it.createdAt,
                        it.updatedAt
                    )
                }?.toTypedArray()
                val menu = Menu(
                    it.title,
                    it.details,
                    it.gender,
                    it.price,
                    it.minutes,
                    it.hairdresserId,
                    hairdresser,
                    menuImages,
                    menuTags,
                    menuTime,
                    it.createdAt,
                    it.updatedAt
                )
                onSuccess?.invoke(menu)
            }, onError, onFailure, onComplete)
    }

    // TODO: パラメータ追加(鎌田)
    // db見て書くところ
    override fun store(
        title: String,
        details: String,
        timeDates: MutableList<String>,
        timeStart: MutableList<String>,
        gender: String,
        price: String,
        minutes: String,
        treatmentIds: MutableList<Int>,
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
        treatmentIds.forEach { queries.add(Pair("treatmentIds[]", it.toString())) }
        timeDates.forEach { queries.add(Pair("timeDates[]", it)) }
        timeStart.forEach { queries.add(Pair("timeStart[]", it)) }
        HttpClient(ModelRegistrationResponse::class.java, HttpMethod.POST, "menus", queries =  *queries.toTypedArray())
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