package training20.tcmobile.mvvm.actions

import training20.tcmobile.net.http.responses.HairdresserRegistrationResponse

interface HairdresserRegistrationFormActions {

    fun onHairdresserRegistrationSuccess(response: HairdresserRegistrationResponse)
}