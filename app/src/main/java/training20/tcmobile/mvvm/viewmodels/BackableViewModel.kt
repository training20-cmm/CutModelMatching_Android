package training20.tcmobile.mvvm.viewmodels

import androidx.lifecycle.ViewModel
import training20.tcmobile.mvvm.actions.BackNavigation
import training20.tcmobile.mvvm.event.EventDispatcher

open class BackableViewModel<BN: BackNavigation>(
    val eventDispatcher: EventDispatcher<BN>
): ViewModel() {

    fun onBack() {
        eventDispatcher.dispatchEvent {
            back()
        }
    }
}