package training20.tcmobile.net.http.responses

class MenuResponse {
    var title: String? = null
    var details: String? = null
    var gender: Char? = null
    var price: Int? = null
    var minutes: Int? = null
    var hairdresserId: Int? = null
    var hairdresser: HairdresserResponse? = null
    var images: Array<MenuImageResponse>? = null
    var tags: Array<MenuTagResponse>? = null
    var time: Array<MenuTimeResponse>? = null
    var createdAt: String? = null
    var updatedAt: String? = null
}