package training20.tcmobile.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.koin.android.ext.android.inject
import training20.tcmobile.R
import training20.tcmobile.databinding.FragmentModelChatRoomBinding
import training20.tcmobile.mvvm.actions.ModelChatRoomActions
import training20.tcmobile.mvvm.viewmodels.ModelChatRoomViewModel

class ModelChatRoomFragment :
    BackableFragment<ModelChatRoomActions, FragmentModelChatRoomBinding, ModelChatRoomViewModel>(),
    ModelChatRoomActions
{

    override val viewModel: ModelChatRoomViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_model_chat_room, container, false)
    }

    override fun createDataBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentModelChatRoomBinding = FragmentModelChatRoomBinding.inflate(inflater, container, false)

    override fun setupViewModel(viewModel: ModelChatRoomViewModel) {
        viewModel.eventDispatcher.bind(viewLifecycleOwner, this)
    }

    override fun setupDataBinding(
        dataBinding: FragmentModelChatRoomBinding,
        savedInstanceState: Bundle?
    ) {
        dataBinding.viewModel = viewModel
    }


}