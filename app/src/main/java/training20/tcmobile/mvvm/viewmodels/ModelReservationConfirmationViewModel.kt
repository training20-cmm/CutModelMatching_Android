package training20.tcmobile.mvvm.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import training20.tcmobile.mvvm.actions.ModelReservationConfirmationActions
import training20.tcmobile.mvvm.event.EventDispatcher
import training20.tcmobile.mvvm.models.Menu
import training20.tcmobile.mvvm.models.Reservation
import training20.tcmobile.mvvm.repositories.MenuRepositoryContract
import training20.tcmobile.mvvm.repositories.MenuRepositoryHttp
import training20.tcmobile.mvvm.repositories.ReservationRepositoryContract
import training20.tcmobile.mvvm.repositories.ReservationRepositoryHttp
import training20.tcmobile.net.http.RequestOptions

class ModelReservationConfirmationViewModel(
    eventDispatcher: EventDispatcher<ModelReservationConfirmationActions>
): BackableViewModel<ModelReservationConfirmationActions>(eventDispatcher) {

    val menu: LiveData<Menu>
        get() = _menu

    private val _menu = MutableLiveData<Menu>()

    fun start(id: Int,timeId:Int) {
        val menuRepository = MenuRepositoryHttp()
        val menuRequestOptions = RequestOptions()
        menuRequestOptions.setEmbedOption("images", "hairdresser.salon", "tags", "time")
        menuRepository.show(id, this::onMenuSuccess, requestOptions = menuRequestOptions)

        //
    }

    fun reservation(menuId :Int,timeId :Int){
        val reservationRepository = ReservationRepositoryHttp()
        reservationRepository.store(menuId, timeId, this::reservationSuccess)
    }

    private fun onMenuSuccess(menu: Menu) {

        _menu.value = menu
        eventDispatcher.dispatchEvent { onMenuCompleted() }
    }

    private  fun reservationSuccess(reservation: Reservation){

    }
}