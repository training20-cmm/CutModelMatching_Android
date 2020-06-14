package training20.tcmobile.mvvm.actions

import training20.tcmobile.net.http.responses.ModelRegistrationResponse

interface ModelRegistrationFormActions: Action {

    fun onModelRegistrationSuccess(response: ModelRegistrationResponse)
}