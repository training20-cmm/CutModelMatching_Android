package training20.tcmobile.mvvm.models

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Model(
    var identifier: String? = null,
    var password: String? = null,
    var email: String? = null,
    var typeId: Int? = null,
    var name: String? = null,
    var bioText: String? = null,
    var gender: String? = null,
    var birthday: String? = null,
    var userId: Int? = null,
    var deletedAt: String? = null,
    var createdAt: String? = null,
    var updatedAt: String? = null
): RealmObject() {

    fun constructor() {}
}