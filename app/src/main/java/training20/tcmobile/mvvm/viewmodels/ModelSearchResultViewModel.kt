package training20.tcmobile.mvvm.viewmodels

import androidx.lifecycle.ViewModel
import training20.tcmobile.SearchCondition
import training20.tcmobile.mvvm.actions.ModelSearchResultActions
import training20.tcmobile.mvvm.event.EventDispatcher
import training20.tcmobile.mvvm.models.Menu
import training20.tcmobile.mvvm.repositories.MenuRepositoryContract

class ModelSearchResultViewModel(
    val eventDispatcher: EventDispatcher<ModelSearchResultActions>,
    private val menuRepository: MenuRepositoryContract
): ViewModel() {

    var menus: Array<Menu>? = null
        private set

    fun start(searchCondition: SearchCondition) {
        menuRepository.search(
            searchCondition.prefecture,
            searchCondition.treatmentIds,
            searchCondition.minPrice,
            searchCondition.maxPrice,
            searchCondition.date,
            searchCondition.minStartTime,
            searchCondition.maxStartTime,
            searchCondition.gender,
            searchCondition.paymentMethodIds,
            searchCondition.salonScale,
            searchCondition.parking,
            this::onMenuSearchSuccess
        )
    }

    private fun onMenuSearchSuccess(menus: Array<Menu>) {
        this.menus = menus
        eventDispatcher.dispatchEvent { onMenuSearchSuccess() }
    }
}