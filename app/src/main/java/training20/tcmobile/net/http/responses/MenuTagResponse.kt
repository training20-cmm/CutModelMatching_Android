package training20.tcmobile.net.http.responses

import training20.tcmobile.mvvm.models.MenuTag

class MenuTagResponse {
    var id: Int? = null
    var name: String? = null
    var color: String? = null
    var createdAt: String? = null
    var updatedAt: String? = null

    fun model(): MenuTag {
        return MenuTag(id, name, color, createdAt, updatedAt)
    }
}