package training20.tcmobile.mvvm.actions

interface HairdresserChatRoomActions: BackAction {

    fun onMessageReceived(message: String?)
    fun onChatMessagesChanged()
}