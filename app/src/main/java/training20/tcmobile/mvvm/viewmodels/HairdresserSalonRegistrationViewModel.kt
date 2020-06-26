package training20.tcmobile.mvvm.viewmodels

import androidx.databinding.Observable
import androidx.databinding.ObservableInt
import training20.tcmobile.R
import training20.tcmobile.mvvm.actions.HairdresserSalonRegistrationActions
import training20.tcmobile.mvvm.event.EventDispatcher
import training20.tcmobile.mvvm.repositories.SalonRepositoryContract
import training20.tcmobile.net.http.HttpClient
import training20.tcmobile.net.http.HttpMethod
import training20.tcmobile.net.http.RequestOptions
import training20.tcmobile.net.http.responses.ErrorResponse
import training20.tcmobile.net.http.responses.HairdresserRegistrationResponse
import training20.tcmobile.net.http.responses.SalonResponse
import java.io.IOException

class SalonMock {
}

class SalonRepositoryHttpMock {

    fun store(
        salonname: String,
        postalcode: String,
        prefecture: String,
        address: String,
        residence: String,
        salonmemo: String,
        seatsnumber: String,
        paymentMethods: MutableList<String>,
        starttime: String,
        endtime: String,
        Starttime2: String,
        endtime2: String,
        week: String,
        onSuccess: ((SalonMock) -> Unit)? = null,
        onError: ((String, Int, ErrorResponse) -> Unit)? = null,
        onFailure: ((IOException) -> Unit)? = null,
        onComplete: (() -> Unit)? = null
    ) {
        val queries = mutableListOf(
            Pair("name", salonname),
            Pair("postcode", postalcode),
            Pair("prefecture", prefecture),
            Pair("address", address),
            Pair("building", residence),
            Pair("bioText", salonmemo),
            Pair("capacity", seatsnumber),
            Pair("parking", "0"),
            Pair("openHoursWeekdays", starttime),
            Pair("closeHoursWeekdays", endtime),
            Pair("openHoursWeekends", Starttime2),
            Pair("closeHoursWeekends", endtime2),
            Pair("regularHoliday", week)
        )
        paymentMethods.forEach { queries.add(Pair("paymentMethodIds[]", it))}
        HttpClient(SalonResponse::class.java, HttpMethod.POST, "salons", queries = *queries.toTypedArray())
            .send({
                onSuccess?.invoke(SalonMock())
            }, onError, onFailure, onComplete)
    }
}

class HairdresserSalonRegistrationViewModel(
    eventDispatcher: EventDispatcher<HairdresserSalonRegistrationActions>
): BackableViewModel<HairdresserSalonRegistrationActions>(eventDispatcher) {
    var imageResource = ObservableInt()
    var salonname = "title"
    var salonmemo = "tt"
    var postalcode = "1234567"
    var prefecturePosition = 0
    var address = "sos"
    var residence = "202"
    var seatsnumber = "5"
    //var gender: ObservableInt? = null
    var cash = false
    var credit = false
    var starttime = "12"
    var endtime = "13"
    var starttime2 = "11"
    var endtime2 = "11"
    var weekPosition = 0
    var prefectures = arrayOf<String>()
    var week = arrayOf<String>()

    val paymentMethods: MutableList<String> = mutableListOf()


    private val salonRepository = SalonRepositoryHttpMock()

    // TODO: サーバから支払方法を取得
    fun registerHairdresser() {
        if (cash == true) {
            paymentMethods.add("1")
        } else if (credit == true) {
            paymentMethods.add("2")
        }

            salonRepository.store(
                salonname,
                postalcode,
                prefectures[prefecturePosition],
                address,
                residence,
                salonmemo,
                seatsnumber,
                paymentMethods,
                starttime,
                endtime,
                starttime2,
                endtime2,
                week[weekPosition],
                onSuccess = this::onSalonStoreSuccess
            )
    }

    fun onSalonStoreSuccess(salon: SalonMock) {
        println(salon)
    }


}