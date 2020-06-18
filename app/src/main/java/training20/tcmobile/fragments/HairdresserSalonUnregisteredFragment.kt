package training20.tcmobile.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.koin.android.ext.android.inject
import training20.tcmobile.R
import training20.tcmobile.databinding.FragmentHairdresserSalonUnregisteredBinding
import training20.tcmobile.mvvm.actions.HairdresserSalonUnregisteredActions
import training20.tcmobile.mvvm.viewmodels.HairdresserSalonUnregisteredViewModel

class HairdresserSalonUnregisteredFragment :
    BackableFragment<HairdresserSalonUnregisteredActions, FragmentHairdresserSalonUnregisteredBinding, HairdresserSalonUnregisteredViewModel>(),
    HairdresserSalonUnregisteredActions
{

    override val viewModel: HairdresserSalonUnregisteredViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_hairdresser_salon_unregistered, container, false)
    }

    override fun createDataBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHairdresserSalonUnregisteredBinding = FragmentHairdresserSalonUnregisteredBinding.inflate(inflater, container, false)

    override fun setupViewModel(viewModel: HairdresserSalonUnregisteredViewModel) {
        viewModel.eventDispatcher.bind(viewLifecycleOwner, this)
    }

    override fun setupDataBinding(
        dataBinding: FragmentHairdresserSalonUnregisteredBinding,
        savedInstanceState: Bundle?
    ) {
        dataBinding.viewModel = viewModel
    }
}