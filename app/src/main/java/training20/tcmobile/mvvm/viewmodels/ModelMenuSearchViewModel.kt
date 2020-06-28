package training20.tcmobile.mvvm.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import training20.tcmobile.mvvm.actions.ModelMenuSearchActions
import training20.tcmobile.mvvm.event.EventDispatcher

class ModelMenuSearchViewModel(
  eventDispatcher: EventDispatcher<ModelMenuSearchActions>
) : BackableViewModel<ModelMenuSearchActions>(eventDispatcher)
{

  val selectedPrefectureItemPosition: LiveData<Int>
          get() = _selectedPrefectureItemPosition

  val menuTreatmentIds: LiveData<MutableList<Int>>
          get() = _menuTreatmentIds

  val minPrice: LiveData<String>
          get() = _minPrice

  val maxPrice: LiveData<String>
          get() = _maxPrice

  val date: LiveData<String>
          get() = _date

  val minTime: LiveData<String>
          get() = _minTime

  val maxTime: LiveData<String>
          get() = _maxTime

  val maleStaff: LiveData<String>
          get() = _maleStaff

  val femaleStaff: LiveData<String>
          get() = _femaleStaff

  val creditCard: LiveData<String>
          get() = _creditCard

  val smallSalon: LiveData<String>
          get() = _smallSalon

  val largeSalon: LiveData<String>
          get() = _largeSalon

  val parking: LiveData<String>
          get() = _parking

  private val _selectedPrefectureItemPosition = MutableLiveData<Int>()
  private val _menuTreatmentIds = MutableLiveData<MutableList<Int>>()
  private val _minPrice = MutableLiveData<String>()
  private val _maxPrice = MutableLiveData<String>()
  private val _date = MutableLiveData<String>()
  private val _minTime = MutableLiveData<String>()
  private val _maxTime = MutableLiveData<String>()
  private val _maleStaff = MutableLiveData<String>()
  private val _femaleStaff = MutableLiveData<String>()
  private val _creditCard = MutableLiveData<String>()
  private val _smallSalon = MutableLiveData<String>()
  private val _largeSalon = MutableLiveData<String>()
  private val _parking = MutableLiveData<String>()

  fun onMenuTreatmentCheckBoxClick(menuTreatmentId: Int) {
    _menuTreatmentIds.value?.add(menuTreatmentId)
  }
}