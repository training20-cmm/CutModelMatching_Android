package training20.tcmobile.auth

import training20.tcmobile.Role
import training20.tcmobile.RoleManager
import training20.tcmobile.mvvm.models.AccessToken
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

    abstract fun currentModel(): Model?

    protected abstract fun save(model: Model)
}