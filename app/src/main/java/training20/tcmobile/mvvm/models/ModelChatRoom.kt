package training20.tcmobile.mvvm.models

class ModelChatRoom(
    val hairdresser: Hairdresser,
    chatMessages: MutableList<ChatMessage>
): ChatRoom(chatMessages)
{
}