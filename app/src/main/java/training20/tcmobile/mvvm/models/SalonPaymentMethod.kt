package training20.tcmobile.mvvm.models

import io.realm.RealmModel
import io.realm.RealmObject
import java.io.Serializable

open class SalonPaymentMethod(
    var id: Int? = null,
    var name: String? = null,
    var createdAt: String? = null,
    var updatedAt: String? = null
): RealmObject(), Serializable {
}