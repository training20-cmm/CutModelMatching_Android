package training20.tcmobile.mvvm.models

class ModelChatRoom(
    val hairdresser: Hairdresser,
    id: Int,
    chatMessages: MutableList<ChatMessage>
): ChatRoom(id, chatMessages)
{
}