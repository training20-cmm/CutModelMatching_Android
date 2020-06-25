package training20.tcmobile.net.http

import android.os.Handler
import android.os.Looper
import com.google.gson.Gson
import okhttp3.*
import training20.tcmobile.BuildConfig
import training20.tcmobile.ErrorCode
import training20.tcmobile.Role
import training20.tcmobile.RoleManager
import training20.tcmobile.net.http.responses.AccessTokenIssuanceResponse
import training20.tcmobile.net.url.Query
import training20.tcmobile.notification.Notification
import training20.tcmobile.notification.NotificationCenter
import training20.tcmobile.notification.NotificationType
import training20.tcmobile.net.http.responses.ErrorResponse
import training20.tcmobile.security.AuthenticationTokenManager
import java.io.IOException
import java.util.concurrent.TimeUnit


class HttpClient<T>(
    private val clazz: Class<T>,
    private val method: HttpMethod,
    private val path: String,
    private val options: RequestOptions? = null,
    private vararg val queries: Pair<String, String>
) {

    companion object {
        /*private const val serverOrigin = "http://192.168.3.2:8080"*/
        /* 会社*/
        private const val serverOrigin = "http://192.168.8.30:8080"
        /*
        private const val serverOrigin = "http://192.168.32.241:8080"
        */
        const val baseUrl = "$serverOrigin/api"
    }

    private var accessTokenIssuanceRequest: Request? = null
    private var role = RoleManager.current()
    private lateinit var request: Request

    private val jsonConverter = Gson()

    init {
        role?.let { role
            val accessTokenIssuanceRequestMediaType =
                MediaType.parse("application/x-www-form-urlencoded")
            val refreshToken =
                AuthenticationTokenManager.getOrLoadRefreshToken(role!!) ?: ""
            val query = Query()
            query.append("refreshToken", refreshToken)
            val accessTokenIssuanceRequestBody =
                RequestBody.create(accessTokenIssuanceRequestMediaType, query.make())
            val url = when(role) {
                Role.HAIRDRESSER -> "$baseUrl/hairdresser_access_tokens"
                Role.MODEL -> "$baseUrl/model_access_tokens"
                else -> ""
            }
            accessTokenIssuanceRequest =
                Request.Builder().post(accessTokenIssuanceRequestBody).url(url).build()
        }
        build()
    }

    fun send(
        onSuccess: ((T) -> Unit)? = null,
        onError: ((String, Int, ErrorResponse) -> Unit)? = null,
        onFailure: ((IOException) -> Unit)? = null,
        onComplete: (() -> Unit)? = null
    ) {
        send(true, onSuccess, onError, onFailure, onComplete)
    }

    private fun build() {
        var builder = Request.Builder()
        role?.let { role ->
            val accessToken = AuthenticationTokenManager.getOrLoadAccessToken(role) ?: ""
            builder = Request.Builder().header(HttpHeader.AUTHORIZATION.value, accessToken)
        }
        var urlQueryString = Query().apply { queries.forEach { append(it.first, it.second) } }.make()
        options?.embed?.let { embed ->
            if (!urlQueryString.isEmpty()) {
                urlQueryString += "&"
            }
            urlQueryString += "embed=${embed}"
        }
        val url = "$baseUrl/$path"
        request = when (method) {
            HttpMethod.POST -> {
                val mediaType =
                    MediaType.parse("application/x-www-form-urlencoded")
                val body = RequestBody.create(mediaType, urlQueryString)
                builder.url(url).post(body).build()
            }
            else -> {
                builder.url("$url?$urlQueryString").build()
            }
        }
    }

    private fun invokeOnError(
        onError: ((String, Int, ErrorResponse) -> Unit)?,
        body: String,
        httpStatusCode: Int,
        errorResponse: ErrorResponse,
        onComplete: (() -> Unit)?
    ) {
        Handler(Looper.getMainLooper()).post {
            onError?.invoke(body, httpStatusCode, errorResponse)
            onComplete?.invoke()
        }
    }

    private fun invokeOnFailure(
        onFailure: ((IOException) -> Unit)?,
        exception: IOException,
        onComplete: (() -> Unit)?
    ) {
        Handler(Looper.getMainLooper()).post {
            onFailure?.invoke(exception)
            onComplete?.invoke()
        }
    }

    private fun invokeOnSuccess(onSuccess: ((T) -> Unit)?, obj: T, onComplete: (() -> Unit)?) {
        Handler(Looper.getMainLooper()).post {
            onSuccess?.invoke(obj)
            onComplete?.invoke()
        }
    }

    private fun issueAccessToken(
        onSuccess: ((T) -> Unit)? = null,
        onError: ((String, Int, ErrorResponse) -> Unit)? = null,
        onFailure: ((IOException) -> Unit)? = null,
        onComplete: (() -> Unit)? = null
    ) {
        createOkHttpClient().newCall(accessTokenIssuanceRequest!!).enqueue(object : Callback {

            override fun onFailure(call: Call, e: IOException) {
                invokeOnFailure(onFailure, e, onComplete)
            }

            override fun onResponse(call: Call, response: Response) {
                val responseBodyString = response.body()?.string() ?: ""
                if (response.code() == HttpStatusCode.BAD_REQUEST.value) {
                    val errorResponse =
                        jsonConverter.fromJson(responseBodyString, ErrorResponse::class.java)
                    invokeOnError(
                        onError,
                        responseBodyString,
                        response.code(),
                        errorResponse,
                        onComplete
                    )
                    when (errorResponse.code) {
                        ErrorCode.EXPIRED_REFRESH_TOKEN.value -> NotificationCenter.notify(
                            NotificationType.EXPIRED_REFRESH_TOKEN,
                            Notification()
                        )
                        ErrorCode.INVALID_REFRESH_TOKEN.value -> NotificationCenter.notify(
                            NotificationType.INVALID_REFRESH_TOKEN,
                            Notification()
                        )
                    }
                } else {
                    val accessTokenIssuanceResponse = jsonConverter.fromJson(
                        responseBodyString,
                        AccessTokenIssuanceResponse::class.java
                    )
                    accessTokenIssuanceResponse.accessToken?.token?.let {
                        AuthenticationTokenManager.putAccessToken(
                            role!!,
                            it
                        )
                    }
                    accessTokenIssuanceResponse.refreshToken?.token?.let {
                        AuthenticationTokenManager.putRefreshToken(
                            role!!,
                            it
                        )
                    }
                    build()
                    send(false, onSuccess, onError, onFailure, onComplete)
                }
            }
        })
    }

    private fun send(
        issuingAccessToken: Boolean,
        onSuccess: ((T) -> Unit)? = null,
        onError: ((String, Int, ErrorResponse) -> Unit)? = null,
        onFailure: ((IOException) -> Unit)? = null,
        onComplete: (() -> Unit)? = null
    ) {
        createOkHttpClient().newCall(request).enqueue(object : Callback {

            override fun onFailure(call: Call, e: IOException) {
                invokeOnFailure(onFailure, e, onComplete)
            }

            override fun onResponse(call: Call, response: Response) {
                val responseBodyString = response.body()?.string() ?: ""
                println(responseBodyString)
                when {
                    response.code() < 400 -> {
                        val obj = jsonConverter.fromJson(responseBodyString, clazz)
                        invokeOnSuccess(onSuccess, obj, onComplete)
                    }
                    else -> {
                        val errorResponse =
                            jsonConverter.fromJson(responseBodyString, ErrorResponse::class.java)
                        when (errorResponse.code) {
                            ErrorCode.INVALID_ACCESS_TOKEN.value, ErrorCode.EXPIRED_ACCESS_TOKEN.value -> {
                                if (issuingAccessToken) {
                                    issueAccessToken(onSuccess, onError, onFailure, onComplete)
                                } else {
                                    invokeOnError(
                                        onError,
                                        responseBodyString,
                                        response.code(),
                                        errorResponse,
                                        onComplete
                                    )
                                }
                            }
                            else -> {
                                invokeOnError(
                                    onError,
                                    responseBodyString,
                                    response.code(),
                                    errorResponse,
                                    onComplete
                                )
                            }
                        }
                    }
                }
            }
        })
    }

    private fun createOkHttpClient(): OkHttpClient {
        return if (BuildConfig.DEBUG) {
            OkHttpClient.Builder()
                .connectTimeout(0, TimeUnit.SECONDS)
                .writeTimeout(0, TimeUnit.SECONDS)
                .readTimeout(0, TimeUnit.SECONDS).build()
        } else {
            OkHttpClient()
        }
    }

}
