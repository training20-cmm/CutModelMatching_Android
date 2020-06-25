package training20.tcmobile.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.activity_model_registration_form.*
import kotlinx.android.synthetic.main.fragment_hairdresser_salon_registration.view.*
import org.koin.android.ext.android.inject
import training20.tcmobile.R
import training20.tcmobile.databinding.FragmentHairdresserSalonRegistrationBinding
import training20.tcmobile.mvvm.MvvmFragment
import training20.tcmobile.mvvm.actions.HairdresserSalonRegistrationActions
import training20.tcmobile.mvvm.viewmodels.HairdresserRegistrationFormViewModel
import training20.tcmobile.mvvm.viewmodels.HairdresserSalonRegistrationViewModel

class HairdresserSalonRegistrationFragment:
    BackableFragment<HairdresserSalonRegistrationActions, FragmentHairdresserSalonRegistrationBinding, HairdresserSalonRegistrationViewModel>(),
    HairdresserSalonRegistrationActions
{

    override val viewModel: HairdresserSalonRegistrationViewModel by inject()

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setupRegistrationButton()
//    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState) ?: return null
        setupRegistrationButton(view)
        return view
    }

    override fun createDataBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHairdresserSalonRegistrationBinding = FragmentHairdresserSalonRegistrationBinding.inflate(inflater, container, false)

    override fun setupViewModel(viewModel: HairdresserSalonRegistrationViewModel) {
        viewModel.eventDispatcher.bind(viewLifecycleOwner, this)
    }

    override fun setupDataBinding(
        dataBinding: FragmentHairdresserSalonRegistrationBinding,
        savedInstanceState: Bundle?
    ) {
        dataBinding.viewModel = viewModel
    }

    private fun setupRegistrationButton(view: View) {
        view.registrationButton.setOnClickListener(this::onRegistrationButtonClicked)
    }

    private fun onRegistrationButtonClicked(v: View) {
        view?.registrationButton?.visibility = View.GONE
        view?.registrationSpinner?.visibility = View.VISIBLE
        viewModel.registerHairdresser()
    }
}