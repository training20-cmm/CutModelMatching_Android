package training20.tcmobile.net.http.responses

import training20.tcmobile.DateTimeEntity

class HairdresserAccessTokenResponse {
    var id: Int? = null
    var hairdresserId: Int? = null
    var token: String? = null
    var expiration: DateTimeEntity? = null
    var createdAt: DateTimeEntity? = null
    var updatedAt: DateTimeEntity? = null
}