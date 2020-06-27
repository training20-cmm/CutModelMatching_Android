package training20.tcmobile.mvvm.repositories

import training20.tcmobile.mvvm.models.MenuTagCategory
import training20.tcmobile.net.http.RequestOptions
import training20.tcmobile.net.http.responses.ErrorResponse
import java.io.IOException

interface MenuTagCategoryRepositoryContract {

    fun index(
        onSuccess: ((Array<MenuTagCategory>) -> Unit)? = null,
        onError: ((String, Int, ErrorResponse) -> Unit)? = null,
        onFailure: ((IOException) -> Unit)? = null,
        onComplete: (() -> Unit)? = null,
        requestOptions: RequestOptions? = null
    )
}