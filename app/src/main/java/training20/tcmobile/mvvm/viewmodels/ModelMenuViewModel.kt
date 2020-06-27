package training20.tcmobile.mvvm.viewmodels

import training20.tcmobile.mvvm.actions.ModelMenuActions
import training20.tcmobile.mvvm.event.EventDispatcher
import training20.tcmobile.mvvm.repositories.MenuRepositoryContract
import training20.tcmobile.mvvm.repositories.ReservationRepositoryContract

class ModelMenuViewModel(
    eventDispatcher: EventDispatcher<ModelMenuActions>,
    private val menuRepository: MenuRepositoryContract,
    private val reservationRepository: ReservationRepositoryContract
): BackableViewModel<ModelMenuActions>(eventDispatcher) {
}