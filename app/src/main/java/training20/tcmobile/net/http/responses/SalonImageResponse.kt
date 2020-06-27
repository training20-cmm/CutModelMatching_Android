package training20.tcmobile.net.http.responses

import training20.tcmobile.mvvm.models.SalonImage

class SalonImageResponse {

    var id: Int? = null
    var path: String? = null
    var salonId: Int? = null
    var createdAt: String? = null
    var updatedAt: String? = null

    fun model(): SalonImage {
        return SalonImage(id, path, salonId, createdAt, updatedAt)
    }
}