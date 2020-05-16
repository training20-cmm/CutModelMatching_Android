package training20.tcmobile.models.model

import training20.tcmobile.exceptions.BusinessRequirementsException

class ModelIdentifier private constructor(val identifier: String) {

    companion object {

        const val maxLength = 32

        fun create(identifier: String): ModelIdentifier {
            if (maxLength < identifier.length) {
                throw BusinessRequirementsException.maxLength(ModelIdentifier::class.java, maxLength)
            }
            return ModelIdentifier(identifier)
        }
    }

}