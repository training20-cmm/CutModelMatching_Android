package training20.tcmobile.ui.recyclerview.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import training20.tcmobile.R
import training20.tcmobile.mvvm.viewmodels.HairdresserChatHistoryViewModel
import training20.tcmobile.ui.recyclerview.viewholders.ChatHistoryRecyclerViewHolder

class ChatHistoryRecyclerViewAdapter(
    private val chatHistoryViewModel: HairdresserChatHistoryViewModel
): RecyclerView.Adapter<ChatHistoryRecyclerViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatHistoryRecyclerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_hairdresser_chat_history_list_item, parent, false)
        return ChatHistoryRecyclerViewHolder(view)
    }

    override fun getItemCount(): Int {
        return chatHistoryViewModel.chatRooms.value?.size ?: 0
    }

    override fun onBindViewHolder(holder: ChatHistoryRecyclerViewHolder, position: Int) {
        val chatRoom = chatHistoryViewModel.chatRooms.value?.get(position)
        holder.nameTextView.text = chatRoom?.model?.name
        holder.messageTextView.text = chatRoom?.chatMessages?.first()?.content
    }

}