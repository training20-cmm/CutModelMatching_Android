package training20.tcmobile.auth

import training20.tcmobile.Role
import training20.tcmobile.RoleManager
import training20.tcmobile.mvvm.models.AccessToken
import training20.tcmobile.mvvm.models.Hairdresser
import training20.tcmobile.mvvm.models.Model
import training20.tcmobile.mvvm.models.RefreshToken
import training20.tcmobile.security.AuthenticationTokenManager
import training20.tcmobile.util.ensureNotNull

abstract class AuthManager {

    fun login(model: Model?, accessToken: String?, refreshToken: String?) {
        ensureNotNull(model, accessToken, refreshToken) { model, accessToken, refreshToken ->
            AuthenticationTokenManager.putAccessToken(Role.MODEL, accessToken)
            AuthenticationTokenManager.putRefreshToken(Role.MODEL, refreshToken)
            RoleManager.setRole(Role.MODEL)
            save(model)
        }
    }

    fun login(hairdresser: Hairdresser?, accessToken: String?, refreshToken: String?) {
        ensureNotNull(hairdresser, accessToken, refreshToken) { hairdresser, accessToken, refreshToken ->
            AuthenticationTokenManager.putAccessToken(Role.HAIRDRESSER, accessToken)
            AuthenticationTokenManager.putRefreshToken(Role.HAIRDRESSER, refreshToken)
            RoleManager.setRole(Role.HAIRDRESSER)
            save(hairdresser)
        }
    }

    abstract fun currentModel(): Model?
    abstract fun currentHairdresser(): Hairdresser?

    protected abstract fun save(model: Model)
    protected abstract fun save(hairdresser: Hairdresser)
}