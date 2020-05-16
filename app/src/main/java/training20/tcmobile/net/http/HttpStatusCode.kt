package training20.tcmobile.net.http

enum class HttpStatusCode(val value: Int) {
    BAD_REQUEST(400),
    UNAUTHORIZED(401);

    companion object {
        fun create(value: Int): HttpStatusCode? {
            return values().firstOrNull { it.value == value }
        }
    }
}