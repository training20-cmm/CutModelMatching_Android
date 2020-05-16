package training20.tcmobile.security


import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import training20.tcmobile.ApplicationContext

class EncryptedStorage(file: PreferenceFile) {

    private var editor: SharedPreferences.Editor? = null

    private val sharedPreferences: SharedPreferences

    init {
        val masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)
        sharedPreferences = EncryptedSharedPreferences.create(
            file.name,
            masterKeyAlias,
            ApplicationContext.context,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
//        editor.putString("accessToken", accessToken.token)
//        editor.putString("refreshToken", refreshToken.token)
//        editor.apply()
    }

    fun apply() {
        editor?.apply()
    }

    fun edit(handler: EncryptedStorage.() -> Unit): EncryptedStorage {
        editor = sharedPreferences.edit()
        handler()
        return this
    }

    fun getString(key: PreferenceKey): String? {
        return sharedPreferences.getString(key.name, null)
    }

    fun putString(key: PreferenceKey, value: String) {
        editor?.putString(key.name, value)
    }
}