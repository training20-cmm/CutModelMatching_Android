package training20.tcmobile.mvvm.viewmodels

import androidx.lifecycle.ViewModel
import training20.tcmobile.mvvm.actions.ModelNotificationsActions
import training20.tcmobile.mvvm.event.EventDispatcher

class ModelNotificationsViewModel(
    val eventDispatcher: EventDispatcher<ModelNotificationsActions>
): ViewModel() {
}