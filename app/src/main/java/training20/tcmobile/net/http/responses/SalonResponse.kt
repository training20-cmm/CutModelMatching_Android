package training20.tcmobile.net.http.responses

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

}