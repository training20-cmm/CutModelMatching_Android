package training20.tcmobile.mvvm.viewmodels

import androidx.lifecycle.ViewModel
import training20.tcmobile.mvvm.actions.HairdresserRegistrationFormActions
import training20.tcmobile.mvvm.event.EventDispatcher
import training20.tcmobile.mvvm.repositories.HairdresserRepositoryContract
import training20.tcmobile.net.http.responses.HairdresserRegistrationResponse

class HairdresserRegistrationFormViewModel(
    val eventDispatcher: EventDispatcher<HairdresserRegistrationFormActions>,
    private val hairdresserRepository: HairdresserRepositoryContract
): ViewModel() {
    var name = ""
    var identifier = ""
    var password = ""
    var passwordConfirmation = ""

    fun registerHairdresser() {
        hairdresserRepository.register(
            identifier,
            password,
            passwordConfirmation,
            name,
            "男",
            "2012-12-23",
            this::onHairdresserRegistrationSuccess
        )
    }

    private fun onHairdresserRegistrationSuccess(response: HairdresserRegistrationResponse) {
        eventDispatcher.dispatchEvent { onHairdresserRegistrationSuccess(response) }
    }
}