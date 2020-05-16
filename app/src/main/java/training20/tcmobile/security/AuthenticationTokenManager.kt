package training20.tcmobile.security

import training20.tcmobile.Perspective
import training20.tcmobile.exceptions.PerspectiveUnknownValueException

object AuthenticationTokenManager {

    private class TokenContainer {
        var accessToken: String? = null
        var refreshToken: String? = null
    }

    private var tokenMap = mapOf(Perspective.HAIRDRESSER to TokenContainer(), Perspective.MODEL to TokenContainer())

    fun getOrLoadAccessToken(perspective: Perspective): String? {
        val tokenContainer = tokenMap[perspective] ?: throw PerspectiveUnknownValueException()
        if (tokenContainer.accessToken == null) {
            tokenContainer.accessToken = EncryptedStorage(PreferenceFile.convert(perspective)).getString(PreferenceKey.ACCESS_TOKEN)
        }
        return tokenContainer.accessToken
    }

    fun getOrLoadRefreshToken(perspective: Perspective): String? {
        val tokenContainer = tokenMap[perspective] ?: throw PerspectiveUnknownValueException()
        if (tokenContainer.refreshToken == null) {
            tokenContainer.refreshToken = EncryptedStorage(PreferenceFile.convert(perspective)).getString(PreferenceKey.REFRESH_TOKEN)
        }
        return tokenContainer.refreshToken
    }

    fun putAccessToken(perspective: Perspective, accessToken: String) {
        val tokenContainer = tokenMap[perspective] ?: throw PerspectiveUnknownValueException()
        tokenContainer.accessToken = accessToken
        EncryptedStorage(PreferenceFile.convert(perspective)).edit {
            putString(PreferenceKey.ACCESS_TOKEN, accessToken)
        }.apply()
    }

    fun putRefreshToken(perspective: Perspective, refreshToken: String) {
        val tokenContainer = tokenMap[perspective] ?: throw PerspectiveUnknownValueException()
        tokenContainer.refreshToken = refreshToken
        EncryptedStorage(PreferenceFile.convert(perspective)).edit {
            putString(PreferenceKey.REFRESH_TOKEN, refreshToken)
        }.apply()
    }

}

