package training20.tcmobile.mvvm.repositories

import android.net.Uri
import io.realm.RealmList
import training20.tcmobile.ApplicationContext
import training20.tcmobile.mvvm.models.Salon
import training20.tcmobile.mvvm.models.SalonImage
import training20.tcmobile.mvvm.models.SalonPaymentMethod
import training20.tcmobile.net.http.HttpClient
import training20.tcmobile.net.http.HttpMethod
import training20.tcmobile.net.http.RequestOptions
import training20.tcmobile.net.http.responses.ErrorResponse
import training20.tcmobile.net.http.responses.SalonResponse
import training20.tcmobile.util.FileUtils
import training20.tcmobile.util.applyNotNull
import java.io.File
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
                val images = if (it.images == null) null else RealmList<SalonImage>().also { realmList ->
                    it.images?.forEach { image ->
                        realmList.add(image.model())
                    }
                }
                val paymentMethods = if (it.paymentMethods == null) null else RealmList<SalonPaymentMethod>().also { realmList ->
                    it.paymentMethods?.forEach { paymentMethod ->
                        realmList.add(paymentMethod.model())
                    }
                }
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

                    images,
                    paymentMethods
                )

                onSuccess?.invoke(salon)
            }, onError, onFailure, onComplete)
    }

    override fun store(
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
        onSuccess: ((Salon) -> Unit)?,
        onError: ((String, Int, ErrorResponse) -> Unit)?,
        onFailure: ((IOException) -> Unit)?,
        onComplete: (() -> Unit)?
    ) {
        val file = File(FileUtils.getPath(ApplicationContext.context, Uri.parse(uri)))
        val files = arrayOf(Pair("images[]", file))
        val queries = mutableListOf(
            Pair("name", salonname),
            Pair("postcode", postalcode),
            Pair("prefecture", prefecture),
            Pair("address", address),
            Pair("building", residence),
            Pair("bioText", salonmemo),
            Pair("capacity", seatsnumber),
            Pair("parking", "0"),
            Pair("images", uri),
            Pair("openHoursWeekdays", starttime),
            Pair("closeHoursWeekdays", endtime),
            Pair("openHoursWeekends", Starttime2),
            Pair("closeHoursWeekends", endtime2),
            Pair("regularHoliday", regularHoliday)
        )
        paymentMethods.forEach { queries.add(Pair("paymentMethodIds[]", it))}
        HttpClient(SalonResponse::class.java, HttpMethod.POST, "salons", files =  files, queries = *queries.toTypedArray())
            .send({
                val salon = it.model()
                onSuccess?.invoke(salon)
            }, onError, onFailure, onComplete)
    }
}