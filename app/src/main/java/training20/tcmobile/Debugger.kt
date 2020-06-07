package training20.tcmobile

import training20.tcmobile.security.AuthenticationTokenManager

object Debugger {

    fun debug(
        role: Role,
        accessToken: String
    ) {
        RoleManager.setRole(role)
        AuthenticationTokenManager.putAccessToken(role, accessToken)
    }
}