package training20.tcmobile.mvvm.viewmodels

import training20.tcmobile.mvvm.actions.HairdresserMenuPostingActions
import training20.tcmobile.mvvm.event.EventDispatcher

class HairdresserMenuPostingViewModel(
    eventDispatcher: EventDispatcher<HairdresserMenuPostingActions>
): BackableViewModel<HairdresserMenuPostingActions>(eventDispatcher)
{
}