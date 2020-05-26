package training20.tcmobile.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_model_registration_form.*
import org.koin.android.ext.android.inject
import training20.tcmobile.Perspective
import training20.tcmobile.R
import training20.tcmobile.databinding.ActivityModelRegistrationFormBinding
import training20.tcmobile.models.model.ModelIdentifier
import training20.tcmobile.models.model.ModelName
import training20.tcmobile.models.model.ModelRawPassword
import training20.tcmobile.net.http.RequestOptions
import training20.tcmobile.net.http.responses.ModelRegistrationResponse
import training20.tcmobile.repositories.ModelRepository
import training20.tcmobile.security.AuthenticationTokenManager
import training20.tcmobile.viewmodels.ModelRegistrationFormViewModel

class ModelRegistrationFormActivity : AppCompatActivity() {

    private inner class RegistrationButtonClickListener: View.OnClickListener {
        override fun onClick(v: View?) {
            registrationButton.visibility = View.GONE
            registrationSpinner.visibility = View.VISIBLE
            val requestOptions = RequestOptions<ModelRegistrationResponse>()
            requestOptions.onSuccess = this@ModelRegistrationFormActivity::onModelRegistrationSuccess
            val modelRepository = ModelRepository()
            modelRepository.register(
                formViewModel.identifier,
                formViewModel.password,
                formViewModel.passwordConfirmation,
                formViewModel.name,
                "女",
                "2012-12-25",
                requestOptions
            )
        }
    }

    private val formViewModel = ViewModelProvider.NewInstanceFactory().create(ModelRegistrationFormViewModel::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindData()
        setupViews()
    }

    private fun bindData() {
        val binding = DataBindingUtil.setContentView<ActivityModelRegistrationFormBinding>(this, R.layout.activity_model_registration_form)
        binding.form = formViewModel
    }

    private fun onModelRegistrationSuccess(response: ModelRegistrationResponse) {
        registrationButton.visibility = View.VISIBLE
        registrationSpinner.visibility = View.GONE
        val accessToken = response.accessToken?.token
        val refreshToken = response.refreshToken?.token
        if (accessToken == null || refreshToken == null) {
            // TODO: Show error message
        } else {
            AuthenticationTokenManager.putAccessToken(Perspective.MODEL, accessToken)
            AuthenticationTokenManager.putRefreshToken(Perspective.MODEL, refreshToken)
            val intent = Intent(this, ModelFoundationActivity::class.java)
            startActivity(intent)
        }
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
