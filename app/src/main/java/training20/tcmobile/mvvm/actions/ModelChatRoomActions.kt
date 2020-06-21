package training20.tcmobile.mvvm.actions

interface ModelChatRoomActions: BackAction {

    fun onChatMessagesChanged()
    fun onMessageReceived(message: String?)
}