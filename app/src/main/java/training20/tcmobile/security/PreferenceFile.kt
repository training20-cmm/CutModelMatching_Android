package training20.tcmobile.security

import training20.tcmobile.Role

enum class PreferenceFile {
    MODEL,
    HAIRDRESSER;

    companion object {
        fun convert(role: Role): PreferenceFile {
            return when(role) {
                Role.HAIRDRESSER -> MODEL
                Role.MODEL -> HAIRDRESSER
            }
        }
    }
}