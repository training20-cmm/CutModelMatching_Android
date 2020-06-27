package training20.tcmobile.net.http.responses

import training20.tcmobile.mvvm.models.MenuTime

class MenuTimeResponse {
    var id: Int? = null
    var date: String? = null
    var start: Int? = null
    var menuId: Int? = null
    var createdAt: String? = null
    var updatedAt: String? = null

    fun model(): MenuTime {
        return MenuTime(
            id, date, start, menuId, createdAt, updatedAt
        )
    }
}