package training20.tcmobile.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.koin.android.ext.android.inject
import training20.tcmobile.R
import training20.tcmobile.databinding.FragmentHairdresserChatRoomBinding
import training20.tcmobile.mvvm.actions.HairdresserChatRoomActions
import training20.tcmobile.mvvm.viewmodels.HairdresserChatRoomViewModel

class HairdresserChatRoomFragment :
    BackableFragment<HairdresserChatRoomActions, FragmentHairdresserChatRoomBinding, HairdresserChatRoomViewModel>(),
    HairdresserChatRoomActions
{

    override val viewModel: HairdresserChatRoomViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_hairdresser_chat_room, container, false)
    }

    override fun createDataBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHairdresserChatRoomBinding = FragmentHairdresserChatRoomBinding.inflate(inflater, container, false)

    override fun setupViewModel(viewModel: HairdresserChatRoomViewModel) {
        viewModel.eventDispatcher.bind(viewLifecycleOwner, this)
    }

    override fun setupDataBinding(
        dataBinding: FragmentHairdresserChatRoomBinding,
        savedInstanceState: Bundle?
    ) {
        dataBinding.viewModel = viewModel
    }
}