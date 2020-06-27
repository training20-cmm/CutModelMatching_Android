package training20.tcmobile.mvvm.models

import io.realm.RealmModel
import io.realm.RealmObject
import java.io.Serializable

open class SalonImage(
    var id: Int? = null,
    var path: String? = null,
    var salonId: Int? = null,
    var createdAt: String? = null,
    var updatedAt: String? = null
): RealmObject(), Serializable {
}