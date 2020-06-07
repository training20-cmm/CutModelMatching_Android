package training20.tcmobile.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_hairdresser_chat_history.view.*
import kotlinx.android.synthetic.main.view_hairdresser_chat_history_list_item.view.*
import training20.tcmobile.R
import training20.tcmobile.net.http.responses.ChatRoomHistoryHairdresserResponse
import training20.tcmobile.repositories.ChatRoomRepository

class HairdresserChatHistoryFragment : Fragment() {

    private class ChatHistoryRecyclerViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val nameTextView = itemView.nameTextView
        val messageTextView = itemView.messageTextView
    }

    private class ChatHistoryRecyclerViewAdapter(
        var chatRooms: Array<ChatRoomHistoryHairdresserResponse>
    ): RecyclerView.Adapter<ChatHistoryRecyclerViewHolder>()
    {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatHistoryRecyclerViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.view_hairdresser_chat_history_list_item, parent, false)
            return ChatHistoryRecyclerViewHolder(view)
        }

        override fun getItemCount(): Int {
            return chatRooms.size
        }

        override fun onBindViewHolder(holder: ChatHistoryRecyclerViewHolder, position: Int) {
            holder.nameTextView.text = chatRooms[position].model?.name
            holder.messageTextView.text = chatRooms[position].chatMessage?.content
        }

    }

    companion object {

        fun newInstance(): HairdresserChatHistoryFragment {
            return HairdresserChatHistoryFragment()
        }

    }

    private val chatHistoryRecyclerViewAdapter = ChatHistoryRecyclerViewAdapter(arrayOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_hairdresser_chat_history, container, false)
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

    override fun onResume() {
        super.onResume()
        val chatRoomRepository = ChatRoomRepository()
        chatRoomRepository.historyHairdresser(
            this::onHistoryHairdresserSuccess
        )
    }

    private fun onHistoryHairdresserSuccess(chatRooms: Array<ChatRoomHistoryHairdresserResponse>) {
        chatHistoryRecyclerViewAdapter.chatRooms = chatRooms
        chatHistoryRecyclerViewAdapter.notifyDataSetChanged()
//        chatHistoryRecyclerView.adapter = ChatHistoryRecyclerViewAdapter(chatRooms)
    }
}
