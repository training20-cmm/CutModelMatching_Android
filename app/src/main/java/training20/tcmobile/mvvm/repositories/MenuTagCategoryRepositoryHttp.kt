package training20.tcmobile.mvvm.repositories

import training20.tcmobile.mvvm.models.MenuTagCategory
import training20.tcmobile.net.http.HttpClient
import training20.tcmobile.net.http.HttpMethod
import training20.tcmobile.net.http.RequestOptions
import training20.tcmobile.net.http.responses.ErrorResponse
import training20.tcmobile.net.http.responses.MenuTagCategoryResponse
import training20.tcmobile.net.http.responses.ModelRegistrationResponse
import java.io.IOException

class MenuTagCategoryRepositoryHttp: MenuTagCategoryRepositoryContract {

    override fun index(
        onSuccess: ((Array<MenuTagCategory>) -> Unit)?,
        onError: ((String, Int, ErrorResponse) -> Unit)?,
        onFailure: ((IOException) -> Unit)?,
        onComplete: (() -> Unit)?,
        requestOptions: RequestOptions?
    ) {
        HttpClient(Array<MenuTagCategoryResponse>::class.java, HttpMethod.GET, "menu_tag_categories", requestOptions)
            .send({
                val menuTagCategories = it.map {
                    MenuTagCategory(
                        it.id,
                        it.name,
                        it.index,
                        it.createdAt,
                        it.updatedAt,
                        it.tags
                    )
                }.toTypedArray()
                onSuccess?.invoke(menuTagCategories)
            }, onError, onFailure, onComplete)
    }
}