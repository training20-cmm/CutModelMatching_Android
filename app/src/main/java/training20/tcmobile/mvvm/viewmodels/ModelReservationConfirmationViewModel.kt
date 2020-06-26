package training20.tcmobile.mvvm.viewmodels

import training20.tcmobile.mvvm.actions.ModelReservationConfirmationActions
import training20.tcmobile.mvvm.event.EventDispatcher

class ModelReservationConfirmationViewModel(
    eventDispatcher: EventDispatcher<ModelReservationConfirmationActions>
): BackableViewModel<ModelReservationConfirmationActions>(eventDispatcher) {
}