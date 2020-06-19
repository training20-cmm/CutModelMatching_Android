package training20.tcmobile.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import io.realm.Realm
import io.realm.RealmConfiguration
import kotlinx.android.synthetic.main.activity_role_selection.*
import training20.tcmobile.RoleManager
import training20.tcmobile.R
import training20.tcmobile.mvvm.models.Model
import training20.tcmobile.mvvm.repositories.SalonRepositoryHttp
import training20.tcmobile.net.http.RequestOptions
import training20.tcmobile.net.http.responses.SalonResponse
import training20.tcmobile.ui.viewpager.adapter.RoleSelectionActivityViewPagerAdapter

class RoleSelectionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_role_selection)
        setupViews()
        RoleManager.setRole(null)

//        val salonRepository = SalonRepositoryHttp()
//        val requestOptions = RequestOptions()
//        requestOptions.setEmbedOption("images", "paymentMethods", "hairdressers")
//        salonRepository.index(this::f, requestOptions = requestOptions)
    }

//    fun f(res: SalonResponse) {
//        print(res)
//    }

    private fun setupViews() {
        viewPager.adapter = RoleSelectionActivityViewPagerAdapter(supportFragmentManager)
        tabLayout.setupWithViewPager(viewPager)
    }
}
