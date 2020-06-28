package training20.tcmobile

import training20.tcmobile.mvvm.models.MenuTreatment

object MenuTreatmentList {

    private val treatmentList = arrayOf(
        MenuTreatment(1, "カット"),
        MenuTreatment(2, "カラー"),
        MenuTreatment(3, "パーマ"),
        MenuTreatment(4, "縮毛矯正"),
        MenuTreatment(5, "エクステ"),
        MenuTreatment(6, "シャンプー"),
        MenuTreatment(7, "トリートメント"),
        MenuTreatment(8, "ヘッドスパ"),
        MenuTreatment(9, "ヘアセット")
    )

    fun all(): Array<MenuTreatment> {
        return treatmentList
    }
}