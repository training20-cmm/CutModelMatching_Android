package training20.tcmobile.mvvm.viewmodels

import training20.tcmobile.mvvm.actions.ModelMenuSearchActions
import training20.tcmobile.mvvm.event.EventDispatcher

class ModelMenuSearchViewModel(
  eventDispatcher: EventDispatcher<ModelMenuSearchActions>
) : BackableViewModel<ModelMenuSearchActions>(eventDispatcher) {
}