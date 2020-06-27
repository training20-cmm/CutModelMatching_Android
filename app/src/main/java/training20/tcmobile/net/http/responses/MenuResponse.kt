package training20.tcmobile.net.http.responses

import training20.tcmobile.mvvm.models.Menu

class MenuResponse {

    var id: Int? = null
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
    var treatment: Array<MenuTreatmentResponse>? = null
    var createdAt: String? = null
    var updatedAt: String? = null

    fun model(): Menu {
        return Menu(
            id, title, details, gender, price, minutes, hairdresserId,
            hairdresser?.model(),
            images?.map{it.model()}?.toTypedArray(),
            tags?.map{it.model()}?.toTypedArray(),
            time?.map{it.model()}?.toTypedArray(),
            treatment?.map{it.model()}?.toTypedArray(),
            createdAt,
            updatedAt
        )
    }
}