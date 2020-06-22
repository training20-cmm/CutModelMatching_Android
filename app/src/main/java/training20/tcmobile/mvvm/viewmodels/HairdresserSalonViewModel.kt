package training20.tcmobile.mvvm.viewmodels

import training20.tcmobile.mvvm.actions.HairdresserSalonActions
import training20.tcmobile.mvvm.event.EventDispatcher
import training20.tcmobile.mvvm.repositories.SalonRepositoryContract

class HairdresserSalonViewModel(
    eventDispatcher: EventDispatcher<HairdresserSalonActions>,
    private val salonRepository: SalonRepositoryContract
) : BackableViewModel<HairdresserSalonActions>(eventDispatcher)
{
}