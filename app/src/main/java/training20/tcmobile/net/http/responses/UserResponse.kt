package training20.tcmobile.net.http.responses

import training20.tcmobile.DateTimeEntity

class UserResponse {

    var hairdresser: HairdresserResponse? = null
    var model: ModelResponse? = null

    var createdAt: DateTimeEntity? = null
    var updatedAt: DateTimeEntity? = null
}