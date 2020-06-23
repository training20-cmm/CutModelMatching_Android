package training20.tcmobile.mvvm.models

import io.realm.RealmObject

open class Hairdresser(
    var id: Int? = null,
    var identifier: String? = null,
    var name: String? = null,
    var email: String? = null,
    var bioText: String? = null,
    var birthday: String? = null,
    var createdAt: String? = null,
    var updatedAt: String? = null
): RealmObject()
{
    fun constructor(){}
}