package training20.tcmobile

import io.realm.RealmList
import io.realm.RealmObject
import java.io.Serializable

open class SearchCondition(
    var prefecture: String? = null,
    var treatmentIds: RealmList<Int>? = null,
    var minPrice: Int? = null,
    var maxPrice: Int? = null,
    var date: String? = null,
    var minStartTime: String? = null,
    var maxStartTime: String? = null,
    var gender: String? = null,
    var paymentMethodIds: RealmList<Int>? = null,
    var salonScale: String? = null,
    var parking: Boolean? = null
): RealmObject(), Serializable {
}