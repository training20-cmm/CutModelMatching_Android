package training20.tcmobile.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.koin.android.ext.android.inject
import training20.tcmobile.R
import training20.tcmobile.databinding.FragmentModelChatHistoryBinding
import training20.tcmobile.mvvm.MvvmFragment
import training20.tcmobile.mvvm.actions.ModelChatHistoryActions
import training20.tcmobile.mvvm.viewmodels.ModelChatHistoryViewModel

class ModelChatHistoryFragment:
    MvvmFragment<FragmentModelChatHistoryBinding, ModelChatHistoryViewModel>(),
    ModelChatHistoryActions
{

    companion object {
        fun newInstance(): ModelChatHistoryFragment {
            return ModelChatHistoryFragment()
        }
    }

    override val viewModel: ModelChatHistoryViewModel by inject()

    override fun createDataBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentModelChatHistoryBinding = FragmentModelChatHistoryBinding.inflate(inflater, container, false)

    override fun setupViewModel(viewModel: ModelChatHistoryViewModel) {
        viewModel.eventDispatcher.bind(viewLifecycleOwner, this)
    }

    override fun setupDataBinding(
        dataBinding: FragmentModelChatHistoryBinding,
        savedInstanceState: Bundle?
    ) {
        dataBinding.viewModel = viewModel
    }
}