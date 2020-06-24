package training20.tcmobile.mvvm.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import training20.tcmobile.mvvm.actions.Action
import training20.tcmobile.mvvm.event.EventDispatcher
import training20.tcmobile.mvvm.repositories.SalonRepositoryContract

class SalonMock

abstract class HairdresserNavigationDrawerViewModel<A: Action>(
    val eventDispatcher: EventDispatcher<A>,
    private val salonRepository: SalonRepositoryContract
): ViewModel() {

    val salon: LiveData<SalonMock>
        get() = _salon

    private val _salon = MutableLiveData<SalonMock>()

    open fun start() {
    }
}