package training20.tcmobile.models.model

class ModelRawPassword private constructor(val rawPassword: String) {

    companion object {

        fun create(rawPassword: String): ModelRawPassword {
            return ModelRawPassword(rawPassword)
        }
    }

}