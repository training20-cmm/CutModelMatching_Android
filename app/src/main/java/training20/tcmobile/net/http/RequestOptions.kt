package training20.tcmobile.net.http

import training20.tcmobile.net.http.responses.ErrorResponse
import java.io.IOException

class RequestOptions {

    var embed: String? = null
        private set

    fun setEmbedOption(vararg relations: String) {
        embed = relations.joinToString(",")
    }
}