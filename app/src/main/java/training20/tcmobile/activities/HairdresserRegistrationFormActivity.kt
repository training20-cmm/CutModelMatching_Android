package training20.tcmobile.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import kotlinx.android.synthetic.main.activity_model_registration_form.*
import org.koin.android.ext.android.inject
import training20.tcmobile.Role
import training20.tcmobile.R
import training20.tcmobile.RoleManager
import training20.tcmobile.databinding.ActivityHairdresserRegistrationFormBinding
import training20.tcmobile.mvvm.MvvmActivity
import training20.tcmobile.mvvm.actions.HairdresserRegistrationFormActions
import training20.tcmobile.net.http.responses.HairdresserRegistrationResponse
import training20.tcmobile.security.AuthenticationTokenManager
import training20.tcmobile.mvvm.viewmodels.HairdresserRegistrationFormViewModel

class HairdresserRegistrationFormActivity:
    MvvmActivity<ActivityHairdresserRegistrationFormBinding, HairdresserRegistrationFormViewModel>(),
    HairdresserRegistrationFormActions
{

    override val viewModel: HairdresserRegistrationFormViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupToolbar()
        setupRegistrationButton()
    }

    override fun createDataBinding(): ActivityHairdresserRegistrationFormBinding
            = DataBindingUtil.setContentView(this, R.layout.activity_hairdresser_registration_form)

    override fun setupViewModel(viewModel: HairdresserRegistrationFormViewModel) {
        viewModel.eventDispatcher.bind(this, this)
    }

    override fun setupDataBinding(
        dataBinding: ActivityHairdresserRegistrationFormBinding,
        savedInstanceState: Bundle?
    ) {
        dataBinding.form = viewModel
    }

    override fun onHairdresserRegistrationSuccess(response: HairdresserRegistrationResponse) {
        registrationButton.visibility = View.VISIBLE
        registrationSpinner.visibility = View.GONE
        val accessToken = response.accessToken?.token
        val refreshToken = response.refreshToken?.token
        if (accessToken == null || refreshToken == null) {
            // TODO: Show error message
        } else {
            AuthenticationTokenManager.putAccessToken(Role.HAIRDRESSER, accessToken)
            AuthenticationTokenManager.putRefreshToken(Role.HAIRDRESSER, refreshToken)
            RoleManager.setRole(Role.HAIRDRESSER)
            val intent = Intent(this, HairdresserMainActivity::class.java)
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
        viewModel.registerHairdresser()
    }
}
