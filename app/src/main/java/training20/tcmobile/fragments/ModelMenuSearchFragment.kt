package training20.tcmobile.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.koin.android.ext.android.inject
import training20.tcmobile.R
import training20.tcmobile.databinding.FragmentModelMenuSearchBinding
import training20.tcmobile.mvvm.actions.ModelMenuSearchActions
import training20.tcmobile.mvvm.viewmodels.ModelMenuSearchViewModel

class ModelMenuSearchFragment :
    BackableFragment<ModelMenuSearchActions, FragmentModelMenuSearchBinding, ModelMenuSearchViewModel>(),
    ModelMenuSearchActions
{

    override val viewModel: ModelMenuSearchViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_model_menu_search, container, false)
    }

    override fun createDataBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentModelMenuSearchBinding = FragmentModelMenuSearchBinding.inflate(inflater, container, false)

    override fun setupViewModel(viewModel: ModelMenuSearchViewModel) {
        viewModel.eventDispatcher.bind(viewLifecycleOwner, this)
    }

    override fun setupDataBinding(
        dataBinding: FragmentModelMenuSearchBinding,
        savedInstanceState: Bundle?
    ) {
        dataBinding.viewModel = viewModel
    }
}