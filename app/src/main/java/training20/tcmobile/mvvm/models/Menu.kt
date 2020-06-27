package training20.tcmobile.mvvm.models

// TODO:　パラメータ追加(鎌田)
class Menu(
    var id: Int? = null,
    var title: String? = null,
    var details: String? = null,
    var gender: Char? = null,
    var price: Int? = null,
    var minutes: Int? = null,
    var hairdresserId: Int? = null,
    val hairdresser: Hairdresser? = null,
    var images: Array<MenuImage>? = null,
    var tags: Array<MenuTag>? = null,
    var time: Array<MenuTime>? = null,
    var treatment: Array<MenuTreatment>? = null,
    var createdAt: String? = null,
    var updatedAt: String? = null
) {
}