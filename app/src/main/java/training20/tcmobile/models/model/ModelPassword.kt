package training20.tcmobile.models.model

import training20.tcmobile.exceptions.BusinessRequirementsException

class ModelPassword private constructor(val password: String) {

    companion object {

        const val length = 60

        fun create(password: String): ModelPassword {
            if (password.length != length) {
                throw BusinessRequirementsException.length(ModelPassword::class.java, length)
            }
            return ModelPassword(password)
        }
    }
}