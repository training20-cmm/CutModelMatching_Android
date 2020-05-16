package training20.tcmobile.models.hairdresser

import training20.tcmobile.exceptions.BusinessRequirementsException

class HairdresserPassword private constructor(val password: String) {

    companion object {

        const val length = 60

        fun create(password: String): HairdresserPassword {
            if (password.length != length) {
                throw BusinessRequirementsException.length(HairdresserPassword::class.java, length)
            }
            return HairdresserPassword(password)
        }
    }
}