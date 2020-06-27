package training20.tcmobile.mvvm.repositories

import android.net.Uri
import training20.tcmobile.ApplicationContext
import training20.tcmobile.mvvm.models.*
import training20.tcmobile.net.http.HttpClient
import training20.tcmobile.net.http.HttpMethod
import training20.tcmobile.net.http.RequestOptions
import training20.tcmobile.net.http.responses.ErrorResponse
import training20.tcmobile.net.http.responses.MenuResponse
import training20.tcmobile.net.http.responses.ModelRegistrationResponse
import training20.tcmobile.util.FileUtils
import java.io.File
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
//                val hairdresser = it.hairdresser?.model()
//                val menuImages = it.images?.map {it.model()}?.toTypedArray()
//                val menuTags = it.tags?.map {it.model()}?.toTypedArray()
//                val menuTime = it.time?.map {it.model()}?.toTypedArray()
//                val menuTreatment = it.treatment?.map{it.model()}?.toTypedArray()
//                val menu = Menu(
//                    it.id,
//                    it.title,
//                    it.details,
//                    it.gender,
//                    it.price,
//                    it.minutes,
//                    it.hairdresserId,
//                    hairdresser,
//                    menuImages,
//                    menuTags,
//                    menuTime,
//                    menuTreatment,
//                    it.createdAt,
//                    it.updatedAt
//                )
                val menu = it.model()
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
        imageUris: MutableList<Uri>,
        tagIds: MutableList<Int>,
        treatmentIds: MutableList<Int>,
        hairdresser_id: Int,
        onSuccess: (() -> Unit)?,
        onError: ((String, Int, ErrorResponse) -> Unit)?,
        onFailure: ((IOException) -> Unit)?,
        onComplete: (() -> Unit)?
    ) {
        val images = imageUris.map {
            Pair("images[]", File(FileUtils.getPath(ApplicationContext.context, it)))
        }.toTypedArray()
        val queries = mutableListOf(
            Pair("title", title),
            Pair("details", details),
            Pair("gender", gender.toString()),
            Pair("price", price),
            Pair("minutes", minutes.toString()),
            Pair("hairdresser_id", hairdresser_id.toString())
        )
        tagIds.forEach{ queries.add(Pair("tagIds[]", it.toString())) }
        treatmentIds.forEach { queries.add(Pair("treatmentIds[]", it.toString())) }
        timeDates.forEach { queries.add(Pair("timeDates[]", it)) }
        timeStart.forEach { queries.add(Pair("timeStart[]", it)) }
        HttpClient(ModelRegistrationResponse::class.java, HttpMethod.POST, "menus", files = images, queries =  *queries.toTypedArray())
            .send({
                onSuccess?.invoke()
            }, onError, onFailure, onComplete)
    }

    override fun search(
        prefecture: String?,
        treatmentIds: MutableList<Int>?,
        minPrice: Int?,
        maxPrice: Int?,
        date: String?,
        minStartTime: String?,
        maxStartTime: String?,
        gender: String?,
        paymentMethodIds: MutableList<Int>?,
        salonScale: String?,
        parking: Boolean?,
        onSuccess: ((Array<Menu>) -> Unit)?,
        onError: ((String, Int, ErrorResponse) -> Unit)?,
        onFailure: ((IOException) -> Unit)?,
        onComplete: (() -> Unit)?
    ) {
        val queries: MutableList<Pair<String, String>> = mutableListOf()
        prefecture?.let { queries.add(Pair("prefecture", it)) }
        treatmentIds?.let { it.forEach { queries.add(Pair("treatmentIds[]", it.toString())) } }
        minPrice?.let { queries.add(Pair("minPrice", it.toString())) }
        maxPrice?.let { queries.add(Pair("maxPrice", it.toString())) }
        date?.let { queries.add(Pair("date", it)) }
        minStartTime?.let { queries.add(Pair("minStartTime", it)) }
        maxStartTime?.let { queries.add(Pair("maxStartTime", it)) }
        gender?.let { queries.add(Pair("gender", gender.toString())) }
        paymentMethodIds?.let { paymentMethodIds.forEach { queries.add(Pair("paymentMethodIds[]", it.toString())) } }
        salonScale?.let { queries.add(Pair("salonScale", it)) }
        parking?.let { queries.add(Pair("parking", it.toString())) }
        HttpClient(Array<MenuResponse>::class.java, HttpMethod.GET, "menus/search", queries =  *queries.toTypedArray())
            .send({
                val menus = it.map { it.model() }.toTypedArray()
                onSuccess?.invoke(menus)
            }, onError, onFailure, onComplete)
    }
}