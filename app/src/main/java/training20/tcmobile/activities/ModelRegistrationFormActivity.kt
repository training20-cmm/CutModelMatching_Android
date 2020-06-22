package training20.tcmobile.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_model_registration_form.*
import kotlinx.android.synthetic.main.activity_model_registration_form.view.*
import org.koin.android.ext.android.inject
import training20.tcmobile.Role
import training20.tcmobile.R
import training20.tcmobile.RoleManager
import training20.tcmobile.auth.AuthManager
import training20.tcmobile.databinding.ActivityModelRegistrationFormBinding
import training20.tcmobile.mvvm.MvvmActivity
import training20.tcmobile.mvvm.actions.ModelRegistrationFormActions
import training20.tcmobile.mvvm.models.AccessToken
import training20.tcmobile.mvvm.models.Model
import training20.tcmobile.mvvm.models.RefreshToken
import training20.tcmobile.net.http.responses.ModelRegistrationResponse
import training20.tcmobile.mvvm.repositories.ModelRepositoryHttp
import training20.tcmobile.security.AuthenticationTokenManager
import training20.tcmobile.mvvm.viewmodels.ModelRegistrationFormViewModel

class ModelRegistrationFormActivity :
    MvvmActivity<ActivityModelRegistrationFormBinding, ModelRegistrationFormViewModel>(),
    ModelRegistrationFormActions
{

    override val viewModel: ModelRegistrationFormViewModel by inject()

    private val authManager: AuthManager by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupToolbar()
        setupRegistrationButton()
    }

    override fun createDataBinding(): ActivityModelRegistrationFormBinding = DataBindingUtil.setContentView<ActivityModelRegistrationFormBinding>(
        this, R.layout.activity_model_registration_form)

    override fun setupViewModel(viewModel: ModelRegistrationFormViewModel) {
        viewModel.eventDispatcher.bind(this, this)
    }

    override fun setupDataBinding(
        dataBinding: ActivityModelRegistrationFormBinding,
        savedInstanceState: Bundle?
    ) {
        dataBinding.form = viewModel
    }

    override fun onModelRegistrationSuccess(model: Model?, accessToken: AccessToken?, refreshToken: RefreshToken?) {
        registrationButton.visibility = View.VISIBLE
        registrationSpinner.visibility = View.GONE
        if (model == null || accessToken == null || refreshToken == null) {
            // TODO: Show error message
        } else {
            authManager.login(model, accessToken.token, refreshToken.token)
            val intent = Intent(this, ModelMainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setupToolbar() {
        setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener {
            finishAfterTransition()
        }
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }

    private fun setupRegistrationButton() {
        registrationButton.setOnClickListener(this::onRegistrationButtonClicked)
    }

    private fun onRegistrationButtonClicked(v: View) {
        registrationButton.visibility = View.GONE
        registrationSpinner.visibility = View.VISIBLE
        viewModel.registerModel()
    }
}
