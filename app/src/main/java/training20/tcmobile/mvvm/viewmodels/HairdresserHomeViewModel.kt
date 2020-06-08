package training20.tcmobile.mvvm.viewmodels

import android.view.MenuItem
import androidx.lifecycle.ViewModel
import com.google.android.material.internal.NavigationMenu
import io.github.yavski.fabspeeddial.FabSpeedDial
import training20.tcmobile.mvvm.actions.HairdresserHomeActions
import training20.tcmobile.mvvm.event.EventDispatcher
import training20.tcmobile.mvvm.repositories.HairdresserHomeRepositoryContract

class HairdresserHomeViewModel(
    val eventDispatcher: EventDispatcher<HairdresserHomeActions>,
    private val repository: HairdresserHomeRepositoryContract
): ViewModel(), FabSpeedDial.MenuListener {

    fun start() {
        if (!repository.isFeatureDiscoveryAlreadyShown) {
            eventDispatcher.dispatchEvent { startFeatureDiscovery() }
        }
    }

    override fun onPrepareMenu(p0: NavigationMenu?): Boolean {
        return true
    }

    override fun onMenuItemSelected(menuItem: MenuItem?): Boolean {
        eventDispatcher.dispatchEvent { showHairstylePosting() }
        return true
    }

    override fun onMenuClosed() {
    }
}