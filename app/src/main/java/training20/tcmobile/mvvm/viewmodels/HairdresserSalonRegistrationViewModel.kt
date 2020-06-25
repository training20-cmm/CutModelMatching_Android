package training20.tcmobile.mvvm.viewmodels

import androidx.databinding.Observable
import androidx.databinding.ObservableInt
import training20.tcmobile.mvvm.actions.HairdresserSalonRegistrationActions
import training20.tcmobile.mvvm.event.EventDispatcher
import training20.tcmobile.net.http.responses.HairdresserRegistrationResponse

class HairdresserSalonRegistrationViewModel(
    eventDispatcher: EventDispatcher<HairdresserSalonRegistrationActions>
): BackableViewModel<HairdresserSalonRegistrationActions>(eventDispatcher) {
    var salonname = ""
    var salonmemo = ""
    var postalcode = ""
    var prefecturePosition = 0
    var address = ""
    var residence = ""
    var seatsnumber = ""
    //var gender: ObservableInt? = null
    var cash = false
    var credit = false
    var starttime = ""
    var endtime = ""
    var starttime2 = ""
    var endtime2 = ""
    var weekPosition = 0


    fun registerHairdresser() {
            print(salonname)
    }

}