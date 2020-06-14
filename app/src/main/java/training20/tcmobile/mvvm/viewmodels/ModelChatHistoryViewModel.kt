package training20.tcmobile.mvvm.viewmodels

import androidx.lifecycle.ViewModel
import training20.tcmobile.mvvm.actions.ModelChatHistoryActions
import training20.tcmobile.mvvm.event.EventDispatcher

class ModelChatHistoryViewModel(
    val eventDispatcher: EventDispatcher<ModelChatHistoryActions>
): ViewModel() {
}