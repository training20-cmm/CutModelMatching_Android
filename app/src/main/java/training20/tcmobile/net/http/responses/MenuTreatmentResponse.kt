package training20.tcmobile.net.http.responses

import training20.tcmobile.mvvm.models.MenuTreatment

class MenuTreatmentResponse {
    var id: Int? = null
    var name: String? = null
    var createdAt: String? = null
    var updatedAt: String? = null

    fun model(): MenuTreatment {
        return MenuTreatment(id, name, createdAt, updatedAt)
    }
}