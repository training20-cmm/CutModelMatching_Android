package training20.tcmobile.net.http.responses

import training20.tcmobile.mvvm.models.Hairdresser

class HairdresserResponse {

    var id: Int? = null
    var identifier: String? = null
    var password: String? = null
    var email: String? = null
    var name: String? = null
    var ruby: String? = null
    var bioText: String? = null
    var speciality: String? = null
    var profileImagePath: String? = null
    var gender: String? = null
    var birthday: String? = null
    var years: Int? = null
    var salonId: Int? = null
    var userId: Int? = null
    var positionId: Int? = null
    var deletedAt: String? = null
    var createdAt: String? = null
    var updatedAt: String? = null

    var age: Int? = null
    var comprehensiveEvaluation: Float? = null

    var salon: SalonResponse? = null
    var position: HairdresserPositionResponse? = null

    fun model(): Hairdresser {
        return Hairdresser(
            id, identifier, password, email, name, ruby, bioText, speciality,
            profileImagePath, gender, birthday, years, salonId, userId, positionId,
            deletedAt, createdAt, updatedAt, age, comprehensiveEvaluation,
            salon?.model(), position?.model()
        )
    }
}