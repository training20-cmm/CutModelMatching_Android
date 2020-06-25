package training20.tcmobile

import io.realm.Realm
import training20.tcmobile.auth.AuthManagerRealm
import training20.tcmobile.mvvm.models.Hairdresser
import training20.tcmobile.mvvm.models.Model
import training20.tcmobile.mvvm.repositories.HairdresserRepositoryHttp
import training20.tcmobile.mvvm.repositories.ModelRepositoryHttp
import training20.tcmobile.security.AuthenticationTokenManager

object Debugger {

    //*******************************************
    // TODO: DI
    val modelRepository = ModelRepositoryHttp()
    val hairdresserRepository = HairdresserRepositoryHttp()
    val authManager = AuthManagerRealm()
    //*******************************************

    private var role: Role? = null
    private var accessToken: String? = null

    fun debug(
        role: Role,
        accessToken: String
    ) {
        this.role = role
        this.accessToken = accessToken
        RoleManager.setRole(role)
        AuthenticationTokenManager.putAccessToken(role, accessToken)
        when (role) {
            Role.HAIRDRESSER -> hairdresserRepository.me(this::onMeSuccess)
            Role.MODEL -> modelRepository.me(this::onMeSuccess)
        }
    }

    private fun onMeSuccess(hairdresser: Hairdresser?) {
        authManager.login(hairdresser, accessToken, "")
    }

    private fun onMeSuccess(model: Model?) {
        authManager.login(model, accessToken, "")
    }
}