package training20.tcmobile.mvvm.viewmodels

import training20.tcmobile.mvvm.actions.HairdresserHairstylePostingActions
import training20.tcmobile.mvvm.event.EventDispatcher

class HairdresserHairstylePostingViewModel(
    eventDispatcher: EventDispatcher<HairdresserHairstylePostingActions>
): BackableViewModel<HairdresserHairstylePostingActions>(eventDispatcher)
{

}