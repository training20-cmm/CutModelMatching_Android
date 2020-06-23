package training20.tcmobile.mvvm.actions

interface ModelChatRoomActions: BackAction {

    fun onMessagesChanged()
    fun onMessageSent(message: String)
    fun onMessageReceived(message: String?)
}