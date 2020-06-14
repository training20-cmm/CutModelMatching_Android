package training20.tcmobile.mvvm.viewmodels

import androidx.lifecycle.ViewModel
import training20.tcmobile.mvvm.actions.ModelHomeActions
import training20.tcmobile.mvvm.event.EventDispatcher

class ModelHomeViewModel(
    val eventDispatcher: EventDispatcher<ModelHomeActions>
): ViewModel() {
}