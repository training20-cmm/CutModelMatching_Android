package training20.tcmobile.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_model_registration_form.*
import training20.tcmobile.Role
import training20.tcmobile.R
import training20.tcmobile.databinding.ActivityHairdresserRegistrationFormBinding
import training20.tcmobile.net.http.responses.HairdresserRegistrationResponse
import training20.tcmobile.mvvm.repositories.HairdresserRepositoryHttp
import training20.tcmobile.security.AuthenticationTokenManager
import training20.tcmobile.mvvm.viewmodels.HairdresserRegistrationFormViewModel

class HairdresserRegistrationFormActivity : AppCompatActivity() {

    private inner class RegistrationButtonClickListener: View.OnClickListener {
        override fun onClick(v: View?) {
            registrationButton.visibility = View.GONE
            registrationSpinner.visibility = View.VISIBLE
//            val responseHandler = ResponseHandler<HairdresserRegistrationResponse>()
//            responseHandler.onSuccess = this@HairdresserRegistrationFormActivity::onUserRegistrationSuccess
            val hairdresserRepository = HairdresserRepositoryHttp()
            hairdresserRepository.register(
                formViewModel.identifier,
                formViewModel.password,
                formViewModel.passwordConfirmation,
                formViewModel.name,
                "男",
                "2012-12-23",
//                responseHandler = responseHandler
                this@HairdresserRegistrationFormActivity::onUserRegistrationSuccess
            )
        }
    }

    private val formViewModel = ViewModelProvider.NewInstanceFactory().create(
        HairdresserRegistrationFormViewModel::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindData()
        setupViews()
    }

    private fun onUserRegistrationSuccess(response: HairdresserRegistrationResponse) {
        registrationButton.visibility = View.VISIBLE
        registrationSpinner.visibility = View.GONE
        val accessToken = response.accessToken?.token
        val refreshToken = response.refreshToken?.token
        if (accessToken == null || refreshToken == null) {
            // TODO: Show error message
        } else {
            AuthenticationTokenManager.putAccessToken(Role.HAIRDRESSER, accessToken)
            AuthenticationTokenManager.putRefreshToken(Role.HAIRDRESSER, refreshToken)
            val intent = Intent(this, HairdresserMainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun bindData() {
        val binding = DataBindingUtil.setContentView<ActivityHairdresserRegistrationFormBinding>(this, R.layout.activity_hairdresser_registration_form)
        binding.form = formViewModel
    }

    private fun setupViews() {
        setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener {
            finishAfterTransition()
        }
        supportActionBar?.setDisplayShowTitleEnabled(false)
        registrationButton.setOnClickListener(RegistrationButtonClickListener())
    }
}
