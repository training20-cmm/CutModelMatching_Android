package training20.tcmobile.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_hairdresser_hairstyle_posting.view.*
import org.koin.android.ext.android.inject
import training20.tcmobile.R
import training20.tcmobile.databinding.FragmentHairdresserHairstylePostingBinding
import training20.tcmobile.mvvm.MvvmFragment
import training20.tcmobile.mvvm.actions.HairdresserHairstylePostingActions
import training20.tcmobile.mvvm.viewmodels.HairdresserHairstylePostingViewModel

class HairdresserHairstylePostingFragment :
    BackableFragment<HairdresserHairstylePostingActions, FragmentHairdresserHairstylePostingBinding, HairdresserHairstylePostingViewModel>(),
    HairdresserHairstylePostingActions
{

    override val viewModel: HairdresserHairstylePostingViewModel by inject()

    override fun createDataBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHairdresserHairstylePostingBinding = FragmentHairdresserHairstylePostingBinding.inflate(inflater, container, false)

    override fun setupViewModel(viewModel: HairdresserHairstylePostingViewModel) {
        viewModel.eventDispatcher.bind(viewLifecycleOwner, this)
    }

    override fun setupDataBinding(
        dataBinding: FragmentHairdresserHairstylePostingBinding,
        savedInstanceState: Bundle?
    ) {
        dataBinding.viewModel = viewModel
    }
}