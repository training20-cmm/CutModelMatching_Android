package training20.tcmobile.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.koin.android.ext.android.inject
import training20.tcmobile.R
import training20.tcmobile.databinding.FragmentHairdresserMenuPostingBinding
import training20.tcmobile.mvvm.actions.HairdresserMenuPostingActions
import training20.tcmobile.mvvm.viewmodels.HairdresserMenuPostingViewModel

class HairdresserMenuPostingFragment :
    BackableFragment<HairdresserMenuPostingActions, FragmentHairdresserMenuPostingBinding, HairdresserMenuPostingViewModel>(),
    HairdresserMenuPostingActions
{

    override val viewModel: HairdresserMenuPostingViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_hairdresser_menu_posting, container, false)
    }

    override fun createDataBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHairdresserMenuPostingBinding = FragmentHairdresserMenuPostingBinding.inflate(inflater, container, false)

    override fun setupViewModel(viewModel: HairdresserMenuPostingViewModel) {
        viewModel.eventDispatcher.bind(viewLifecycleOwner, this)
    }

    override fun setupDataBinding(
        dataBinding: FragmentHairdresserMenuPostingBinding,
        savedInstanceState: Bundle?
    ) {
        dataBinding.viewModel = viewModel
    }
}