package training20.tcmobile.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_hairdresser_chat_history.view.*
import org.koin.android.ext.android.inject
import training20.tcmobile.databinding.FragmentHairdresserChatHistoryBinding
import training20.tcmobile.mvvm.MvvmFragment
import training20.tcmobile.mvvm.actions.HairdresserChatHistoryActions
import training20.tcmobile.mvvm.viewmodels.HairdresserChatHistoryViewModel
import training20.tcmobile.ui.recyclerview.adapters.ChatHistoryRecyclerViewAdapter

class HairdresserChatHistoryFragment :
    MvvmFragment<FragmentHairdresserChatHistoryBinding, HairdresserChatHistoryViewModel>(),
    HairdresserChatHistoryActions
{

    companion object {

        fun newInstance(): HairdresserChatHistoryFragment {
            return HairdresserChatHistoryFragment()
        }

    }

    override val viewModel: HairdresserChatHistoryViewModel by inject()

    private val chatHistoryRecyclerViewAdapter = ChatHistoryRecyclerViewAdapter(viewModel)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState) ?: return null
        val chatHistoryRecyclerView = view.chatHistoryRecyclerView
        chatHistoryRecyclerView.layoutManager = LinearLayoutManager(activity)
        chatHistoryRecyclerView.chatHistoryRecyclerView.adapter = chatHistoryRecyclerViewAdapter
        val divider = DividerItemDecoration(
            chatHistoryRecyclerView.context,
            LinearLayoutManager(activity).orientation
            )
        chatHistoryRecyclerView.addItemDecoration(divider)
        return view
    }

    override fun createDataBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHairdresserChatHistoryBinding = FragmentHairdresserChatHistoryBinding.inflate(inflater, container, false)

    override fun setupViewModel(viewModel: HairdresserChatHistoryViewModel) {
        viewModel.eventDispatcher.bind(viewLifecycleOwner, this)
        viewModel.start()
    }

    override fun setupDataBinding(
        dataBinding: FragmentHairdresserChatHistoryBinding,
        savedInstanceState: Bundle?
    ) {
    }

    override fun onChatRoomsChanged() {
        chatHistoryRecyclerViewAdapter.notifyDataSetChanged()
    }
}
