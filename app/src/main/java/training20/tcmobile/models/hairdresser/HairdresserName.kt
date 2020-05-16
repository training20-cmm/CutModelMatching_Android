package training20.tcmobile.models.hairdresser

import training20.tcmobile.exceptions.BusinessRequirementsException

class HairdresserName private constructor(val name: String) {

    companion object {

        const val maxLength = 64

        fun create(name: String): HairdresserName {
            if (maxLength < name.length) {
                throw BusinessRequirementsException.maxLength(HairdresserName::class.java, maxLength)
            }
            return HairdresserName(name)
        }
    }

}