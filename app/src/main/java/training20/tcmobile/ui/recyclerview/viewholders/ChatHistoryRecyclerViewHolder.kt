package training20.tcmobile.ui.recyclerview.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.view_hairdresser_chat_history_list_item.view.*

class ChatHistoryRecyclerViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    val nameTextView = itemView.nameTextView
    val messageTextView = itemView.messageTextView
}