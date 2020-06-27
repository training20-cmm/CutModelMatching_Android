package training20.tcmobile.net.http.responses

import io.realm.RealmList
import training20.tcmobile.mvvm.models.Salon
import training20.tcmobile.mvvm.models.SalonImage
import training20.tcmobile.mvvm.models.SalonPaymentMethod

class SalonResponse {

    var id: Int? = null
    var name: String? = null
    var postcode: String? = null
    var prefecture: String? = null
    var address: String? = null
    var building: String? = null
    var bioText: String? = null
    var capacity: Int? = null
    var parking: Int? = null
    var openHoursWeekdays: Int? = null
    var closeHoursWeekdays: Int? = null
    var openHoursWeekends: Int? = null
    var closeHoursWeekends: Int? = null
    var regularHoliday: String? = null
    var createdAt: String? = null
    var updatedAt: String? = null

    var images: Array<SalonImageResponse>? = null
    var paymentMethods: Array<SalonPaymentMethodResponse>? = null
    var hairdressers: Array<HairdresserResponse>? = null

    fun model(): Salon {
        val imageModels = images?.map { it.model() }
        val imageRealmList = if (imageModels == null) null else RealmList<SalonImage>().also { realmList ->
            imageModels.forEach{ imageModel -> realmList.add(imageModel) }
        }
        val paymentMethodModels = paymentMethods?.map { it.model() }
        val paymentMethodRealmList = if (paymentMethodModels == null) null else RealmList<SalonPaymentMethod>().also { realmList ->
            paymentMethodModels.forEach{ paymentMethodModel -> realmList.add(paymentMethodModel)}
        }
        return Salon(id, name, postcode, prefecture, address, building, bioText, capacity, openHoursWeekdays, closeHoursWeekdays, openHoursWeekends, closeHoursWeekends, regularHoliday,
            imageRealmList, paymentMethodRealmList)
    }

}