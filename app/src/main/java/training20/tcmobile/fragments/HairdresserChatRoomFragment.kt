package training20.tcmobile.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_hairdresser_chat_room.*
import kotlinx.android.synthetic.main.fragment_hairdresser_chat_room.messageEditText
import kotlinx.android.synthetic.main.fragment_hairdresser_chat_room.view.*
import kotlinx.android.synthetic.main.fragment_model_chat_room.*
import kotlinx.android.synthetic.main.view_hairdresser_chat_room_list_item.view.*
import org.json.JSONObject
import org.koin.android.ext.android.inject
import training20.tcmobile.R
import training20.tcmobile.auth.AuthManager
import training20.tcmobile.databinding.FragmentHairdresserChatRoomBinding
import training20.tcmobile.databinding.FragmentModelChatRoomBinding
import training20.tcmobile.mvvm.actions.HairdresserChatRoomActions
import training20.tcmobile.mvvm.viewmodels.HairdresserChatRoomViewModel
import training20.tcmobile.mvvm.viewmodels.ModelChatRoomViewModel
import training20.tcmobile.util.ensureNotNull

class HairdresserChatRoomFragment :
    BackableFragment<HairdresserChatRoomActions, FragmentHairdresserChatRoomBinding, HairdresserChatRoomViewModel>(),
    HairdresserChatRoomActions
{

    private class RecyclerViewHolder(item: View): RecyclerView.ViewHolder(item) {
        val incommingMessageTextView = item.incomingMessageTextView
        val outgoingMessageTextView = item.outgoingMessageTextView
    }

    private class RecyclerViewAdapter(
        private val viewModel: HairdresserChatRoomViewModel
    ): RecyclerView.Adapter<RecyclerViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.view_hairdresser_chat_room_list_item, parent, false)
            return RecyclerViewHolder(view)
        }

        override fun getItemCount(): Int {
            return viewModel.chatMessages.value?.size ?: 0
        }

        override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
            viewModel.chatMessages.value?.get(position)?.let { chatMessage ->
                if (chatMessage.isIncoming) {
                    holder.incommingMessageTextView.text = chatMessage.content
                    holder.incommingMessageTextView.visibility = View.VISIBLE
                    holder.outgoingMessageTextView.visibility = View.GONE
                } else {
                    holder.outgoingMessageTextView.text = chatMessage.content
                    holder.outgoingMessageTextView.visibility = View.VISIBLE
                    holder.incommingMessageTextView.visibility = View.GONE
                }
            }
        }

    }

    private inner class SendButtonClickListener: View.OnClickListener {

        override fun onClick(v: View?) {
            ensureNotNull(authManager.currentHairdresser()?.userId, args.model.userId) { myUserId, partnerUserId ->
                val message = messageEditText.text.toString()
                viewModel.sendMessage(message, myUserId, partnerUserId)
                messageEditText.setText("")
            }
        }
    }

    override val viewModel: HairdresserChatRoomViewModel by inject()

    private val authManager: AuthManager by inject()
    private val args: HairdresserChatRoomFragmentArgs by navArgs()
    private val recyclerViewAdapter = RecyclerViewAdapter(viewModel)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState) ?: return null
        view.chatRoomRecyclerView.adapter = recyclerViewAdapter
        view.chatRoomRecyclerView.layoutManager = LinearLayoutManager(activity)
        view.sendButton.setOnClickListener(SendButtonClickListener())
        return view
    }

    override fun createDataBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHairdresserChatRoomBinding = FragmentHairdresserChatRoomBinding.inflate(inflater, container, false)

    override fun setupViewModel(viewModel: HairdresserChatRoomViewModel) {
        viewModel.eventDispatcher.bind(viewLifecycleOwner, this)
        args.model.userId?.let { userId ->
            viewModel.start(args.chatRoomId, userId)
        }
    }

    override fun setupDataBinding(
        dataBinding: FragmentHairdresserChatRoomBinding,
        savedInstanceState: Bundle?
    ) {
        dataBinding.viewModel = viewModel
    }

    override fun onMessagesChanged() {
        recyclerViewAdapter.notifyDataSetChanged()
        view?.chatRoomRecyclerView?.smoothScrollToPosition(recyclerViewAdapter.itemCount)
    }

    override fun onMessageSent(message: String) {
        recyclerViewAdapter.notifyDataSetChanged()
        view?.chatRoomRecyclerView?.smoothScrollToPosition(recyclerViewAdapter.itemCount)
    }

    override fun onMessageReceived(message: String?) {
        recyclerViewAdapter.notifyDataSetChanged()
    }
}