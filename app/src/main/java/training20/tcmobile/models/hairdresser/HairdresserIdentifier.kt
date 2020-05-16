package training20.tcmobile.models.hairdresser

import training20.tcmobile.exceptions.BusinessRequirementsException

class HairdresserIdentifier private constructor(val identifier: String) {

    companion object {

        const val maxLength = 32

        fun create(identifier: String): HairdresserIdentifier {
            if (maxLength < identifier.length) {
                throw BusinessRequirementsException.maxLength(HairdresserIdentifier::class.java, maxLength)
            }
            return HairdresserIdentifier(identifier)
        }
    }

}