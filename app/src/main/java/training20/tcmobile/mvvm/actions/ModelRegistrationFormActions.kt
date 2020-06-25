package training20.tcmobile.mvvm.actions

import training20.tcmobile.mvvm.models.AccessToken
import training20.tcmobile.mvvm.models.Model
import training20.tcmobile.mvvm.models.RefreshToken

interface ModelRegistrationFormActions: Action {

    fun onModelRegistrationSuccess(model: Model?, accessToken: AccessToken?, refreshToken: RefreshToken?)
}