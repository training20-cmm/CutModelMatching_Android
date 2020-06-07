package training20.tcmobile.security

import training20.tcmobile.Role
import training20.tcmobile.exceptions.PerspectiveUnknownValueException

object AuthenticationTokenManager {

    private class TokenContainer {
        var accessToken: String? = null
        var refreshToken: String? = null
    }

    private var tokenMap = mapOf(Role.HAIRDRESSER to TokenContainer(), Role.MODEL to TokenContainer())

    fun getOrLoadAccessToken(role: Role): String? {
        val tokenContainer = tokenMap[role] ?: throw PerspectiveUnknownValueException()
        if (tokenContainer.accessToken == null) {
            tokenContainer.accessToken = EncryptedStorage(PreferenceFile.convert(role)).getString(PreferenceKey.ACCESS_TOKEN)
        }
        return tokenContainer.accessToken
    }

    fun getOrLoadRefreshToken(role: Role): String? {
        val tokenContainer = tokenMap[role] ?: throw PerspectiveUnknownValueException()
        if (tokenContainer.refreshToken == null) {
            tokenContainer.refreshToken = EncryptedStorage(PreferenceFile.convert(role)).getString(PreferenceKey.REFRESH_TOKEN)
        }
        return tokenContainer.refreshToken
    }

    fun putAccessToken(role: Role, accessToken: String) {
        val tokenContainer = tokenMap[role] ?: throw PerspectiveUnknownValueException()
        tokenContainer.accessToken = accessToken
        EncryptedStorage(PreferenceFile.convert(role)).edit {
            putString(PreferenceKey.ACCESS_TOKEN, accessToken)
        }.apply()
    }

    fun putRefreshToken(role: Role, refreshToken: String) {
        val tokenContainer = tokenMap[role] ?: throw PerspectiveUnknownValueException()
        tokenContainer.refreshToken = refreshToken
        EncryptedStorage(PreferenceFile.convert(role)).edit {
            putString(PreferenceKey.REFRESH_TOKEN, refreshToken)
        }.apply()
    }

}

