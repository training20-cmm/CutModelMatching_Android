package training20.tcmobile.mvvm.models

class HairdresserChatRoom(
    val model: Model,
    id: Int,
    chatMessages: MutableList<ChatMessage>
): ChatRoom(id, chatMessages)
{
}