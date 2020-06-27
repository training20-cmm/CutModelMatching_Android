package training20.tcmobile.mvvm.models

import io.realm.RealmList
import io.realm.RealmObject
import training20.tcmobile.net.http.responses.SalonImageResponse
import training20.tcmobile.net.http.responses.SalonPaymentMethodResponse
import java.io.Serializable

open class Salon(
    var id: Int? = null,
    var name: String? = null,
    var postcode: String? = null,
    var prefecture: String? = null,
    var address: String? = null,
    var building: String? = null,
    var bioText: String? = null,
    var capacity: Int? = null,

    var openHoursWeekdays: Int? = null,
    var closeHoursWeekdays: Int? = null,
    var openHoursWeekends: Int? = null,
    var closeHoursWeekends: Int? = null,

    var regularHoliday: String? = null,

    var images: RealmList<SalonImage>? = null,
    var paymentMethods: RealmList<SalonPaymentMethod>? = null
): RealmObject(), Serializable {
}