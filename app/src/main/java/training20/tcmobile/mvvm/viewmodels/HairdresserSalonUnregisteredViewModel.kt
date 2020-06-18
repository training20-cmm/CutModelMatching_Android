package training20.tcmobile.mvvm.viewmodels

import training20.tcmobile.mvvm.actions.HairdresserSalonUnregisteredActions
import training20.tcmobile.mvvm.event.EventDispatcher

class HairdresserSalonUnregisteredViewModel(
    eventDispatcher: EventDispatcher<HairdresserSalonUnregisteredActions>
):
    BackableViewModel<HairdresserSalonUnregisteredActions>(eventDispatcher)
{
}