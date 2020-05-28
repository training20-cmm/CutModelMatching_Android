package training20.tcmobile.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_chat_history.view.*

import training20.tcmobile.R
import training20.tcmobile.models.ChatMessage

class ChatHistoryFragment : Fragment() {

    private class RecyclerViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    }

    private class RecyclerViewAdapter(val chatMessages: Array<ChatMessage>): RecyclerView.Adapter<RecyclerViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.view_chat_history_list_item, parent, false)
            return RecyclerViewHolder(view)
        }

        override fun getItemCount(): Int {
            return chatMessages.size
        }

        override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {

        }

    }

    companion object {

        fun newInstance(): ChatHistoryFragment {
            return ChatHistoryFragment()
        }

    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_chat_history, container, false)
    }

}
