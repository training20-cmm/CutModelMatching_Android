package training20.tcmobile.mvvm.actions

interface ModelChatRoomActions: BackAction {

    fun onMessagesChanged()
    fun onMessageReceived(message: String?, isIncoming: Boolean)
}