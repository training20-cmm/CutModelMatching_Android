package training20.tcmobile.mvvm.viewmodels

import training20.tcmobile.mvvm.actions.HairdresserSalonRegistrationActions
import training20.tcmobile.mvvm.event.EventDispatcher

class HairdresserSalonRegistrationViewModel(
    eventDispatcher: EventDispatcher<HairdresserSalonRegistrationActions>
): BackableViewModel<HairdresserSalonRegistrationActions>(eventDispatcher) {
}