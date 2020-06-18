package training20.tcmobile.mvvm.viewmodels

import training20.tcmobile.mvvm.actions.HairdresserChatRoomActions
import training20.tcmobile.mvvm.event.EventDispatcher

class HairdresserChatRoomViewModel(
    eventDispatcher: EventDispatcher<HairdresserChatRoomActions>
): BackableViewModel<HairdresserChatRoomActions>(eventDispatcher) {
}