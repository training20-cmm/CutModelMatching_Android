package training20.tcmobile.mvvm.viewmodels

import android.view.View
import androidx.lifecycle.ViewModel
import kotlinx.android.synthetic.main.activity_model_registration_form.view.*
import training20.tcmobile.mvvm.actions.ModelRegistrationFormActions
import training20.tcmobile.mvvm.event.EventDispatcher
import training20.tcmobile.mvvm.repositories.ModelRepositoryContract
import training20.tcmobile.mvvm.repositories.ModelRepositoryHttp
import training20.tcmobile.net.http.responses.ModelRegistrationResponse

class ModelRegistrationFormViewModel(
   val eventDispatcher: EventDispatcher<ModelRegistrationFormActions>,
   private val modelRepository: ModelRepositoryContract
): ViewModel()
{
    var name = ""
    var identifier = ""
    var password = ""
    var passwordConfirmation = ""

    fun registerModel() {
        modelRepository.register(
            identifier,
            password,
            passwordConfirmation,
            name,
            "女",
            "2012-12-25",
            this::onModelRegistrationSuccess
        )
    }

    private fun onModelRegistrationSuccess(response: ModelRegistrationResponse) {
        eventDispatcher.dispatchEvent { onModelRegistrationSuccess(response) }
    }
}