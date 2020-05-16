package training20.tcmobile.security

import training20.tcmobile.Perspective
import training20.tcmobile.exceptions.PerspectiveUnknownValueException

enum class PreferenceFile {
    MODEL,
    HAIRDRESSER;

    companion object {
        fun convert(perspective: Perspective): PreferenceFile {
            return when(perspective) {
                Perspective.HAIRDRESSER -> MODEL
                Perspective.MODEL -> HAIRDRESSER
            }
        }
    }
}