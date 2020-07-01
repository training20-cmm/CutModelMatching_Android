package training20.tcmobile.mvvm.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import training20.tcmobile.mvvm.actions.ModelHomeActions
import training20.tcmobile.mvvm.event.EventDispatcher
import training20.tcmobile.mvvm.models.Reservation
import training20.tcmobile.mvvm.repositories.ReservationRepositoryContract
import training20.tcmobile.net.http.responses.ErrorResponse

class ModelHomeViewModel(
    val eventDispatcher: EventDispatcher<ModelHomeActions>,
    private val reservationRepository: ReservationRepositoryContract
): ViewModel() {

    val nextReservation: LiveData<Reservation>
        get() = _nextReservation

    private val _nextReservation = MutableLiveData<Reservation>()

    fun start() {
        reservationRepository.next(this::onReservationNextSuccess, this::onReservationNextError)
    }

    private fun onReservationNextSuccess(reservation: Reservation) {
        this._nextReservation.value = reservation
        eventDispatcher.dispatchEvent { onNextReservationFetched() }
    }

    private fun onReservationNextError(body: String, code: Int, errorResponse: ErrorResponse) {
        eventDispatcher.dispatchEvent { onNextReservationNotFound() }
    }

}