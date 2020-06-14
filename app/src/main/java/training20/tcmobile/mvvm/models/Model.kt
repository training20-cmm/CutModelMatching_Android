package training20.tcmobile.mvvm.models

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Model(
    var id: Int? = null,
    var identifier: String? = null,
    var name: String? = null,
    var bioText: String? = null,
    var birthday: String? = null,
    var email: String? = null,
    var createdAt: String? = null,
    var updatedAt: String? = null
): RealmObject() {

    fun constructor() {}
}