package training20.tcmobile.mvvm.viewmodels

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.realm.Realm
import io.realm.RealmList
import training20.tcmobile.SearchCondition
import training20.tcmobile.mvvm.actions.ModelMenuSearchActions
import training20.tcmobile.mvvm.event.EventDispatcher

class ModelMenuSearchViewModel(
  eventDispatcher: EventDispatcher<ModelMenuSearchActions>
) : BackableViewModel<ModelMenuSearchActions>(eventDispatcher)
{

    val selectedPrefectureItemPosition = MutableLiveData(0)
    val minPrice = MutableLiveData("")
    val maxPrice = MutableLiveData("")
    val selectedMinTimeItemPosition = MutableLiveData(0)
    val selectedMaxTimeItemPosition = MutableLiveData(0)
    val maleStaff = MutableLiveData(false)
    val femaleStaff = MutableLiveData(false)
    val creditCard = MutableLiveData(false)
    val smallSalon = MutableLiveData(false)
    val largeSalon = MutableLiveData(false)
    val parking = MutableLiveData(false)

    val prefectures = MutableLiveData<Array<String>>(arrayOf())
    val timeList = MutableLiveData<Array<String>>(arrayOf())

    val date: LiveData<String>
        get() = _date

    val menuTreatmentIds: LiveData<MutableList<Int>>
        get() = _menuTreatmentIds

    private val _menuTreatmentIds = MutableLiveData<MutableList<Int>>(mutableListOf())
    private val _date = MutableLiveData("")

    fun addDate(year: Int, month: Int, day: Int) {
        _date.value = "${year}-${month}-${day}"
    }

    fun onMenuTreatmentCheckBoxClick(menuTreatmentId: Int) {
        _menuTreatmentIds.value?.add(menuTreatmentId)
    }

    fun onSearchButtonClicked() {
        val searchCondition = SearchCondition()
        selectedPrefectureItemPosition.value?.let {
            searchCondition.prefecture = prefectures.value?.get(it)
        }
        searchCondition.minPrice = minPrice.value?.toIntOrNull()
        searchCondition.maxPrice = maxPrice.value?.toIntOrNull()
        searchCondition.date = date.value
        selectedMinTimeItemPosition.value?.let {
            timeList.value?.get(it)?.toIntOrNull()?.let { hour ->
                searchCondition.minStartTime = "$hour:00"
            }
        }
        selectedMaxTimeItemPosition.value?.let {
            timeList.value?.get(it)?.toIntOrNull()?.let { hour ->
                searchCondition.maxStartTime = "$hour:00"
            }
        }
        searchCondition.gender = if (maleStaff.value != null && maleStaff.value!!) "男"
            else if (femaleStaff.value != null && femaleStaff.value!!) "女"
            else null
        searchCondition.salonScale = if (largeSalon.value != null && largeSalon.value!!) "large"
            else if (smallSalon.value != null && smallSalon.value!!) "small"
            else null
        searchCondition.parking = if (parking.value != null && parking.value!!) true else null
        val realm = Realm.getDefaultInstance()
        realm.executeTransaction {
            realm.delete(SearchCondition::class.java)
            realm.copyToRealm(searchCondition)
        }
        eventDispatcher.dispatchEvent { onConditionSaved() }
    }
}