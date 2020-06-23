package training20.tcmobile.mvvm.actions

interface HairdresserChatRoomActions: BackAction {

    fun onMessagesChanged()
    fun onMessageSent(message: String)
    fun onMessageReceived(message: String?)
}