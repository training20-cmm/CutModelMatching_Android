package training20.tcmobile.net.http.responses

class MenuResponse {
    var title: String? = null
    var details: String? = null
    var timeDates: Array<String>? = null
    var timeStart: Array<String>? = null
    var gender: Char? = null
    var price: Int? = null
    var minutes: Int? = null
    var hairdresserId: Int? = null
    var images: Array<MenuImageResponse>? = null
    var Tag: Array<MenuTagResponse>? = null
    var Time: Array<MenuTimeResponse>? = null
    var createdAt: String? = null
    var updatedAt: String? = null
}