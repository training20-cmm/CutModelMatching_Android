package training20.tcmobile.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.koin.android.ext.android.inject
import training20.tcmobile.R
import training20.tcmobile.databinding.FragmentModelMenuBinding
import training20.tcmobile.mvvm.actions.ModelMenuActions
import training20.tcmobile.mvvm.viewmodels.ModelMenuViewModel

class ModelMenuFragment :
    BackableFragment<ModelMenuActions, FragmentModelMenuBinding, ModelMenuViewModel>(),
    ModelMenuActions
{

    override val viewModel: ModelMenuViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_model_menu, container, false)
    }

    override fun createDataBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentModelMenuBinding = FragmentModelMenuBinding.inflate(inflater, container, false)

    override fun setupViewModel(viewModel: ModelMenuViewModel) {
        viewModel.eventDispatcher.bind(viewLifecycleOwner, this)
    }

    override fun setupDataBinding(
        dataBinding: FragmentModelMenuBinding,
        savedInstanceState: Bundle?
    ) {
        dataBinding.viewModel = viewModel
    }


}