package training20.tcmobile.models.model

import training20.tcmobile.exceptions.BusinessRequirementsException

class ModelName private constructor(val name: String) {

    companion object {

        const val maxLength = 64

        fun create(name: String): ModelName {
            if (maxLength < name.length) {
                throw BusinessRequirementsException.maxLength(ModelName::class.java, maxLength)
            }
            return ModelName(name)
        }
    }

}