package training20.tcmobile.net.http.responses

import training20.tcmobile.mvvm.models.Reservation

class ReservationResponse {

    var id: Int? = null
    var menuId: Int? = null
    var menuTimeId: Int? = null
    var modelId: Int? = null
    var createdAt: String? = null
    var updatedAt: String? = null

    var menu: MenuResponse? = null

    fun model(): Reservation {
        return Reservation(id, menuId, menuTimeId, modelId, createdAt, updatedAt, menu?.model())
    }
}