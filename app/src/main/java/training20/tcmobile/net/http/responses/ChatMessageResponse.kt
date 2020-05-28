package training20.tcmobile.net.http.responses

import training20.tcmobile.DateTimeEntity

class ChatMessageResponse {

    var id: Int? = null
    var content: String? = null
    var imagePath: String? = null
    var senderUserId: Int? = null
    var receiverUserId: Int? = null
    var createdAt: DateTimeEntity? = null
    var updatedAt: DateTimeEntity? = null

    var senderUser: UserResponse? = null
    var receiverUser: UserResponse? = null
}