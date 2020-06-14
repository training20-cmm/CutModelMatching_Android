package training20.tcmobile.mvvm.models

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class User(
    var identifier: String? = null,
    var email: String? = null,
    val createdAt: String? = null,
    val updatedAt: String? = null
) {

    @PrimaryKey
    private var realmId = 0L
}