package training20.tcmobile.net.http.responses

import training20.tcmobile.mvvm.models.HairdresserPosition

class HairdresserPositionResponse {
    var id: Int? = null
    var name: String? = null
    var createdAt: String? = null
    var updatedAt: String? = null

    fun model(): HairdresserPosition {
        return HairdresserPosition(id, name, createdAt, updatedAt)
    }
}