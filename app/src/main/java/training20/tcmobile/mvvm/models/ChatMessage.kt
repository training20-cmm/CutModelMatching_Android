package training20.tcmobile.mvvm.models

class ChatMessage(
    val id: Int? = null,
    val content: String? = null,
    val imagePath: String? = null,
    val userId: Int? = null,
    val createdAt: String? = null,
    val updatedAt: String? = null,
    var isIncoming: Boolean = false
) {
}