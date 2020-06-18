package training20.tcmobile.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.koin.android.ext.android.inject
import training20.tcmobile.R
import training20.tcmobile.databinding.FragmentHairdresserSalonBinding
import training20.tcmobile.mvvm.MvvmFragment
import training20.tcmobile.mvvm.actions.HairdresserSalonActions
import training20.tcmobile.mvvm.viewmodels.BackableViewModel
import training20.tcmobile.mvvm.viewmodels.HairdresserSalonViewModel

class HairdresserSalonFragment :
    BackableFragment<HairdresserSalonActions, FragmentHairdresserSalonBinding, BackableViewModel<HairdresserSalonActions>>(),
    HairdresserSalonActions
{

    override val viewModel: HairdresserSalonViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_hairdresser_salon, container, false)
    }

    override fun createDataBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHairdresserSalonBinding = FragmentHairdresserSalonBinding.inflate(inflater, container, false)

    override fun setupViewModel(viewModel: BackableViewModel<HairdresserSalonActions>) {
        viewModel.eventDispatcher.bind(viewLifecycleOwner, this)
    }

    override fun setupDataBinding(
        dataBinding: FragmentHairdresserSalonBinding,
        savedInstanceState: Bundle?
    ) {
        dataBinding.viewModel = viewModel
    }
}