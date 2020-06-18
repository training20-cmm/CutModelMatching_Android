package training20.tcmobile.mvvm.viewmodels

import training20.tcmobile.mvvm.actions.HairdresserSalonActions
import training20.tcmobile.mvvm.event.EventDispatcher

class HairdresserSalonViewModel(
    eventDispatcher: EventDispatcher<HairdresserSalonActions>
) : BackableViewModel<HairdresserSalonActions>(eventDispatcher)
{
}