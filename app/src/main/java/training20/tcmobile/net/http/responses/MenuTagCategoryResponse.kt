package training20.tcmobile.net.http.responses

import training20.tcmobile.mvvm.models.MenuTag

class MenuTagCategoryResponse {
    var id: Int? = null
    var name: String? = null
    var index: Int? = null
    var createdAt: String? = null
    var updatedAt: String? = null
    var tags: MutableList<MenuTag>? = null
}