package training20.tcmobile.mvvm.viewmodels

import androidx.lifecycle.ViewModel
import training20.tcmobile.mvvm.actions.HairdresserHairstyleListActions
import training20.tcmobile.mvvm.event.EventDispatcher

class HairdresserHairstyleListViewModel(
    val eventDispatcher: EventDispatcher<HairdresserHairstyleListActions>
) : ViewModel()
{
}