package training20.tcmobile.mvvm.models

import training20.tcmobile.net.http.responses.SalonImageResponse
import training20.tcmobile.net.http.responses.SalonPaymentMethodResponse

class Salon(
    val id: Int? = null,
    var name: String? = null,
    val postcode: String? = null,
    val prefecture: String? = null,
    val address: String? = null,
    val building: String? = null,
    val bioText: String? = null,
    val capacity: Int? = null,

    val openHoursWeekdays: Int? = null,
    val closeHoursWeekdays: Int? = null,
    val openHoursWeekends: Int? = null,
    val closeHoursWeekends: Int? = null,

    val regularHoliday: String? = null,

    val images: Array<SalonImageResponse>? = null,
    val paymentMethods: Array<SalonPaymentMethodResponse>? = null
) {
}