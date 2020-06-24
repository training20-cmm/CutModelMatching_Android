package training20.tcmobile.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_hairdresser_hairstyle_posting.view.*
import kotlinx.android.synthetic.main.fragment_hairdresser_hairstyle_posting.view.toolbar
import kotlinx.android.synthetic.main.view_toolbar.view.*
import org.koin.android.ext.android.inject
import training20.tcmobile.ApplicationContext
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

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState) ?: return null
        view.toolbarBackButton.setOnClickListener {
            viewModel.onBack()
        }
        view.toolbarTitleTextView.text = "TEST TITLE"
        val toolbarRightButton = view.toolbarRightButton
        toolbarRightButton.visibility = View.VISIBLE
        toolbarRightButton.text = "編集"
        toolbarRightButton.setBackgroundColor(ContextCompat.getColor(ApplicationContext.context, R.color.colorAccent))
        toolbarRightButton.setOnClickListener {
            println("Clicked")
        }
        return view
    }

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