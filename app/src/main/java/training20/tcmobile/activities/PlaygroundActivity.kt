package training20.tcmobile.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import training20.tcmobile.R
import training20.tcmobile.mvvm.models.Menu
import training20.tcmobile.mvvm.models.Reservation
import training20.tcmobile.mvvm.repositories.MenuRepositoryHttp
import training20.tcmobile.mvvm.repositories.ReservationRepositoryHttp
import training20.tcmobile.net.http.RequestOptions

class PlaygroundActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_playground)

        //***********************************************
        // NOTE: メニューの詳細
        val menuRepository = MenuRepositoryHttp()
        val menuRequestOptions = RequestOptions()
        menuRequestOptions.setEmbedOption("images", "hairdresser.salon", "tags", "time")
        menuRepository.show(1, this::m, requestOptions = menuRequestOptions)
        //***********************************************


        val reservationRepository = ReservationRepositoryHttp()
        //***********************************************
        // NOTE: 予約一覧
        reservationRepository.index(this::r)
        //***********************************************
        // NOTE: 予約
        reservationRepository.store(1, 11, this::rs)
        //***********************************************
    }

    private fun m(menu: Menu) {
        println(menu)
    }

    private fun r(reservationList: Array<Reservation>) {
        println(reservationList)
    }

    private fun rs(reservation: Reservation) {
        println(reservation)
    }
}