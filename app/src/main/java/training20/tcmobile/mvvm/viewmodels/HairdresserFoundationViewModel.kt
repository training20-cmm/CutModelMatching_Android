package training20.tcmobile.mvvm.viewmodels

import android.view.MenuItem
import androidx.lifecycle.ViewModel
import training20.tcmobile.R
import training20.tcmobile.mvvm.actions.HairdresserFoundationActions
import training20.tcmobile.mvvm.event.EventDispatcher


class HairdresserFoundationViewModel(
    val eventDispatcher: EventDispatcher<HairdresserFoundationActions>
): ViewModel() {

    fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.navigationHome -> {
                eventDispatcher.dispatchEvent { showHome() }
                return true
            }
            R.id.navigationNotifications -> {
                eventDispatcher.dispatchEvent { showNotifications() }
                return true
            }
            R.id.navigationChatHistory -> {
                eventDispatcher.dispatchEvent { showChatHistory() }
                return true
            }
        }
        return false
    }
}