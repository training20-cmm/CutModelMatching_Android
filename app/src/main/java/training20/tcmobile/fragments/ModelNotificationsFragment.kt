package training20.tcmobile.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.koin.android.ext.android.inject
import training20.tcmobile.R
import training20.tcmobile.databinding.FragmentModelNotificationsBinding
import training20.tcmobile.mvvm.MvvmFragment
import training20.tcmobile.mvvm.actions.ModelNotificationsActions
import training20.tcmobile.mvvm.viewmodels.ModelNotificationsViewModel

class ModelNotificationsFragment:
    MvvmFragment<FragmentModelNotificationsBinding, ModelNotificationsViewModel>(),
    ModelNotificationsActions
{

    companion object {
        fun newInstance(): ModelNotificationsFragment {
            return ModelNotificationsFragment()
        }
    }

    override val viewModel: ModelNotificationsViewModel by inject()

    override fun createDataBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentModelNotificationsBinding = FragmentModelNotificationsBinding.inflate(inflater, container, false)

    override fun setupViewModel(viewModel: ModelNotificationsViewModel) {
        viewModel.eventDispatcher.bind(viewLifecycleOwner, this)
    }

    override fun setupDataBinding(
        dataBinding: FragmentModelNotificationsBinding,
        savedInstanceState: Bundle?
    ) {
        dataBinding.viewModel = viewModel
    }

}