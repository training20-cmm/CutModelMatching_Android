package training20.tcmobile.mvvm.viewmodels

import training20.tcmobile.mvvm.actions.ModelMenuActions
import training20.tcmobile.mvvm.event.EventDispatcher

class ModelMenuViewModel(
    eventDispatcher: EventDispatcher<ModelMenuActions>
): BackableViewModel<ModelMenuActions>(eventDispatcher) {
}