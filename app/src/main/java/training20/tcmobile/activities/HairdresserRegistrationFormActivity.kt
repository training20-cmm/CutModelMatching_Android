package training20.tcmobile.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.model_activity_registration_form.*
import org.koin.android.ext.android.inject
import training20.tcmobile.Perspective
import training20.tcmobile.R
import training20.tcmobile.databinding.HairdresserActivityRegistrationFormBinding
import training20.tcmobile.models.hairdresser.HairdresserIdentifier
import training20.tcmobile.models.hairdresser.HairdresserName
import training20.tcmobile.models.hairdresser.HairdresserRawPassword
import training20.tcmobile.net.http.responses.HairdresserRegistrationResponse
import training20.tcmobile.repositories.HairdresserRepository
import training20.tcmobile.security.AuthenticationTokenManager
import training20.tcmobile.viewmodels.HairdresserRegistrationFormViewModel

class HairdresserRegistrationFormActivity : AppCompatActivity() {

    private inner class RegistrationButtonClickListener: View.OnClickListener {
        override fun onClick(v: View?) {
            registrationButton.visibility = View.GONE
            registrationSpinner.visibility = View.VISIBLE
            hairdresserRepository.register(
                HairdresserName.create(formViewModel.name),
                HairdresserIdentifier.create(formViewModel.identifier),
                HairdresserRawPassword.create(formViewModel.password),
                HairdresserRawPassword.create(formViewModel.passwordConfirmation),
                this@HairdresserRegistrationFormActivity::onUserRegistrationSuccess
            )
        }
    }

    private val formViewModel = ViewModelProvider.NewInstanceFactory().create(
        HairdresserRegistrationFormViewModel::class.java)
    private val hairdresserRepository: HairdresserRepository by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindData()
        setupViews()
    }

    private fun onUserRegistrationSuccess(response: HairdresserRegistrationResponse) {
        registrationButton.visibility = View.VISIBLE
        registrationSpinner.visibility = View.GONE
        val accessToken = response.hairdresserAccessToken?.token
        val refreshToken = response.hairdresserRefreshToken?.token
        if (accessToken == null || refreshToken == null) {
            // TODO: Show error message
        } else {
            AuthenticationTokenManager.putAccessToken(Perspective.HAIRDRESSER, accessToken)
            AuthenticationTokenManager.putRefreshToken(Perspective.HAIRDRESSER, refreshToken)
            val intent = Intent(this, HairdresserHomeActivity::class.java)
            startActivity(intent)
        }
    }

    private fun bindData() {
        val binding = DataBindingUtil.setContentView<HairdresserActivityRegistrationFormBinding>(this, R.layout.hairdresser_activity_registration_form)
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
