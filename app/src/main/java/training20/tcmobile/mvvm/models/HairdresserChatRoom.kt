package training20.tcmobile.mvvm.models

class HairdresserChatRoom(
    val model: Model,
    chatMessages: MutableList<ChatMessage>
): ChatRoom(chatMessages)
{
}