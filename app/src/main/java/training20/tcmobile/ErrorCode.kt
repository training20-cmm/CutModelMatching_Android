package training20.tcmobile

enum class ErrorCode(val value: Int) {
    INVALID_REFRESH_TOKEN(1),
    EXPIRED_REFRESH_TOKEN(2),
    INVALID_ACCESS_TOKEN(3),
    EXPIRED_ACCESS_TOKEN(4);

    companion object {
        fun create(value: Int): ErrorCode? {
            return values().firstOrNull { it.value == value }
        }
    }
}