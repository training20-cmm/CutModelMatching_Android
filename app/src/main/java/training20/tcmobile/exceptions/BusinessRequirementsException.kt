package training20.tcmobile.exceptions

class BusinessRequirementsException(reason: String): Exception(reason) {

    companion object {

        fun length(resource: Class<*>, length: Int): BusinessRequirementsException {
            return BusinessRequirementsException("$resource must be $length characters exactly")
        }

        fun maxLength(resource: Class<*>, max: Int): BusinessRequirementsException {
            return BusinessRequirementsException("$resource must be $max characters or less")
        }

    }
}