package training20.tcmobile.ui.fabspeeddial

import android.view.MenuItem
import com.google.android.material.internal.NavigationMenu
import io.github.yavski.fabspeeddial.FabSpeedDial
import training20.tcmobile.mvvm.actions.HairdresserHomeActions
import training20.tcmobile.mvvm.event.EventDispatcher

class HairdresserHomeFabSpeedDialMenuListener(
    private val eventDispatcher: EventDispatcher<HairdresserHomeActions>
): FabSpeedDial.MenuListener {

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