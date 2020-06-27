package training20.tcmobile.net.http.responses

import training20.tcmobile.mvvm.models.MenuImage

class MenuImageResponse {
    var id: String? = null
    var path: String? = null
    var menuId: Int? = null
    var createdAt: String? = null
    var updatedAt: String? = null

    fun model(): MenuImage {
        return MenuImage(id, path, menuId, createdAt, updatedAt)
    }
}