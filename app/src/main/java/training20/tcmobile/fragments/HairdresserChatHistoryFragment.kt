package training20.tcmobile.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_hairdresser_chat_history.view.*
import kotlinx.android.synthetic.main.view_hairdresser_chat_history_list_item.view.*
import org.koin.android.ext.android.inject
import training20.tcmobile.R
import training20.tcmobile.databinding.FragmentHairdresserChatHistoryBinding
import training20.tcmobile.mvvm.MvvmFragment
import training20.tcmobile.mvvm.actions.HairdresserChatHistoryActions
import training20.tcmobile.mvvm.models.HairdresserChatRoom
import training20.tcmobile.mvvm.viewmodels.HairdresserChatHistoryViewModel

class HairdresserChatHistoryFragment :
    MvvmFragment<FragmentHairdresserChatHistoryBinding, HairdresserChatHistoryViewModel>(),
    HairdresserChatHistoryActions
{

    private class RecyclerViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val constraintLayout = itemView.chatHistoryListItemConstraintLayout
        val nameTextView = itemView.nameTextView
        val messageTextView = itemView.messageTextView
    }

    private inner class RecyclerViewAdapter(
        private val chatHistoryViewModel: HairdresserChatHistoryViewModel
    ): RecyclerView.Adapter<RecyclerViewHolder>()
    {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.view_hairdresser_chat_history_list_item, parent, false)
            return RecyclerViewHolder(view)
        }

        override fun getItemCount(): Int {
            return chatHistoryViewModel.chatRooms.value?.size ?: 0
        }

        override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
            val chatRoom = chatHistoryViewModel.chatRooms.value?.get(position)
            holder.nameTextView.text = chatRoom?.model?.name
            holder.messageTextView.text = chatRoom?.chatMessages?.first()?.content
            holder.constraintLayout.setOnClickListener {
                this@HairdresserChatHistoryFragment.onChatHistoryListItemClicked(chatHistoryViewModel.chatRooms.value?.get(position))
            }
        }

    }

    companion object {

        fun newInstance(): HairdresserChatHistoryFragment {
            return HairdresserChatHistoryFragment()
        }

    }

    override val viewModel: HairdresserChatHistoryViewModel by inject()

    private val chatHistoryRecyclerViewAdapter = RecyclerViewAdapter(viewModel)

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
        dataBinding.viewModel = viewModel
    }

    override fun onChatRoomsChanged() {
        chatHistoryRecyclerViewAdapter.notifyDataSetChanged()
    }

    private fun onChatHistoryListItemClicked(chatRoom: HairdresserChatRoom?) {
        chatRoom?.let { chatRoom ->
            findNavController().navigate(R.id.action_hairdresserFoundationFragment_to_hairdresserChatRoomFragment)
        }
    }
}
