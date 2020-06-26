package training20.tcmobile.mvvm.models

// TODO:　パラメータ追加(鎌田)
class Menu(
    var title: String? = null,
    var details: String? = null,
    var timeDates: Array<String>? = null,
    var timeStart: Array<String>? = null,
    var gender: Char? = null,
    var price: Int? = null,
    var minutes: Int? = null,
    var hairdresserId: Int? = null,
    var images: Array<MenuImage>? = null,
    var Tag: Array<MenuTag>? = null,
    var Time: Array<MenuTime>? = null,
    var createdAt: String? = null,
    var updatedAt: String? = null
) {
}