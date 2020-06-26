package training20.tcmobile.mvvm.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import training20.tcmobile.mvvm.actions.HairdresserSalonActions
import training20.tcmobile.mvvm.event.EventDispatcher
import training20.tcmobile.mvvm.models.Salon
import training20.tcmobile.mvvm.repositories.SalonRepositoryContract
import training20.tcmobile.net.http.RequestOptions
import training20.tcmobile.net.http.responses.SalonResponse
import training20.tcmobile.util.applyNotNull

class HairdresserSalonViewModel(
    eventDispatcher: EventDispatcher<HairdresserSalonActions>,
    private val salonRepository: SalonRepositoryContract
) : BackableViewModel<HairdresserSalonActions>(eventDispatcher)
{
    val salon: LiveData<Salon>
        get() = _salon

    private val _salon = MutableLiveData<Salon>()

    fun start() {
        val requestOptions = RequestOptions()
        requestOptions.setEmbedOption("images", "paymentMethods")
        //api接続
        salonRepository.index(this::onSalonSuccess,requestOptions = requestOptions)
    }

    private fun onSalonSuccess(salon: Salon) {
//        _salon.value = applyNotNull(salon.id,salon){
//
//        }

        _salon.value = salon
        //apiからのデータ取得が成功したことをFragmentに知らせる
        eventDispatcher.dispatchEvent { onSalonCompleted() }
    }


}