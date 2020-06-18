package training20.tcmobile.mvvm.viewmodels

import training20.tcmobile.mvvm.actions.ModelChatRoomActions
import training20.tcmobile.mvvm.event.EventDispatcher

class ModelChatRoomViewModel(
    eventDispatcher: EventDispatcher<ModelChatRoomActions>
) : BackableViewModel<ModelChatRoomActions>(eventDispatcher) {
}