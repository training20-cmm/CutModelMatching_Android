package training20.tcmobile.models.hairdresser

class HairdresserRawPassword private constructor(val rawPassword: String) {

    companion object {

        fun create(rawPassword: String): HairdresserRawPassword {
            return HairdresserRawPassword(rawPassword)
        }
    }

}