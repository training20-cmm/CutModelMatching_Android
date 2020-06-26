package training20.tcmobile.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.koin.android.ext.android.inject
import training20.tcmobile.R
import training20.tcmobile.databinding.FragmentModelReservationConfirmationBinding
import training20.tcmobile.mvvm.MvvmFragment
import training20.tcmobile.mvvm.actions.ModelReservationConfirmationActions
import training20.tcmobile.mvvm.viewmodels.ModelReservationConfirmationViewModel

class ModelReservationConfirmationFragment :
    BackableFragment<ModelReservationConfirmationActions, FragmentModelReservationConfirmationBinding, ModelReservationConfirmationViewModel>(),
    ModelReservationConfirmationActions
{

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override val viewModel: ModelReservationConfirmationViewModel by inject()

    override fun createDataBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentModelReservationConfirmationBinding = FragmentModelReservationConfirmationBinding.inflate(inflater, container, false)

    override fun setupViewModel(viewModel: ModelReservationConfirmationViewModel) {
        viewModel.eventDispatcher.bind(viewLifecycleOwner, this)
    }

    override fun setupDataBinding(
        dataBinding: FragmentModelReservationConfirmationBinding,
        savedInstanceState: Bundle?
    ) {
        dataBinding.viewModel = viewModel
    }
}