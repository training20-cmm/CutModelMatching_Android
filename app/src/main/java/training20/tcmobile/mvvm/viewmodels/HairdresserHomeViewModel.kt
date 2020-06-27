package training20.tcmobile.mvvm.viewmodels

import android.view.MenuItem
import androidx.lifecycle.ViewModel
import com.google.android.material.internal.NavigationMenu
import io.github.yavski.fabspeeddial.FabSpeedDial
import training20.tcmobile.mvvm.actions.HairdresserHomeActions
import training20.tcmobile.mvvm.event.EventDispatcher
import training20.tcmobile.mvvm.models.Salon
import training20.tcmobile.mvvm.repositories.HairdresserHomeRepositoryContract
import training20.tcmobile.mvvm.repositories.SalonRepositoryContract
import training20.tcmobile.net.http.responses.ErrorResponse

class HairdresserHomeViewModel(
    val eventDispatcher: EventDispatcher<HairdresserHomeActions>,
    private val repository: HairdresserHomeRepositoryContract,
    private val salonRepository: SalonRepositoryContract
): ViewModel() {

    var salon: Salon? = null

    fun start() {
        salonRepository.index(this::onSalonIndexSuccess)
    }

    private fun onSalonIndexSuccess(salon: Salon) {
        this.salon = salon
    }

}