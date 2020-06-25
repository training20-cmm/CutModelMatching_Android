package training20.tcmobile.mvvm.viewmodels

import android.view.MenuItem
import androidx.lifecycle.ViewModel
import com.google.android.material.internal.NavigationMenu
import io.github.yavski.fabspeeddial.FabSpeedDial
import training20.tcmobile.mvvm.actions.HairdresserHomeActions
import training20.tcmobile.mvvm.event.EventDispatcher
import training20.tcmobile.mvvm.repositories.HairdresserHomeRepositoryContract
import training20.tcmobile.mvvm.repositories.SalonRepositoryContract

class HairdresserHomeViewModel(
    eventDispatcher: EventDispatcher<HairdresserHomeActions>,
    salonRepository: SalonRepositoryContract,
    private val repository: HairdresserHomeRepositoryContract
): HairdresserNavigationDrawerViewModel<HairdresserHomeActions>(eventDispatcher, salonRepository) {

    override fun start() {
        super.start()
    }
}