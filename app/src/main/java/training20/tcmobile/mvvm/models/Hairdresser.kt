package training20.tcmobile.mvvm.models

import io.realm.RealmObject
import java.io.Serializable

open class Hairdresser(
    var id: Int? = null,
    var identifier: String? = null,
    var password: String? = null,
    var email: String? = null,
    var name: String? = null,
    var ruby: String? = null,
    var bioText: String? = null,
    var speciality: String? = null,
    var profileImagePath: String? = null,
    var gender: String? = null,
    var birthday: String? = null,
    var years: Int? = null,
    var salonId: Int? = null,
    var userId: Int? = null,
    var positionId: Int? = null,
    var deletedAt: String? = null,
    var createdAt: String? = null,
    var updatedAt: String? = null,
    var age: Int? = null,
    var comprehensiveEvaluation: Float? = null,
    var salon: Salon? = null,
    var position: HairdresserPosition? = null
): RealmObject(), Serializable
{
    fun constructor(){}
}