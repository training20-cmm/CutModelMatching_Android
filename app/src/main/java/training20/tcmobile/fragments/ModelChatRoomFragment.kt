package training20.tcmobile.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import io.realm.Realm
import kotlinx.android.synthetic.main.fragment_model_chat_room.*
import kotlinx.android.synthetic.main.fragment_model_chat_room.view.*
import kotlinx.android.synthetic.main.fragment_model_chat_room.view.messageEditText
import kotlinx.android.synthetic.main.view_model_chat_room_list_item.view.*
import kotlinx.android.synthetic.main.view_model_chat_room_list_item.view.incomingMessageTextView
import kotlinx.android.synthetic.main.view_model_chat_room_list_item.view.outgoingMessageTextView
import org.java_websocket.client.WebSocketClient
import org.java_websocket.handshake.ServerHandshake
import org.json.JSONObject
import org.koin.android.ext.android.inject
import training20.tcmobile.R
import training20.tcmobile.auth.AuthManager
import training20.tcmobile.databinding.FragmentModelChatRoomBinding
import training20.tcmobile.mvvm.actions.ModelChatRoomActions
import training20.tcmobile.mvvm.models.ChatMessage
import training20.tcmobile.mvvm.models.Model
import training20.tcmobile.mvvm.models.User
import training20.tcmobile.mvvm.viewmodels.ModelChatRoomViewModel
import training20.tcmobile.util.ensureNotNull
import java.net.URI

class ModelChatRoomFragment :
    BackableFragment<ModelChatRoomActions, FragmentModelChatRoomBinding, ModelChatRoomViewModel>(),
    ModelChatRoomActions
{

    private class RecyclerViewHolder(item: View): RecyclerView.ViewHolder(item) {
        val iconImageView = item.iconImageView
        val incomingMessageTextView = item.incomingMessageTextView
        val incomingMessageDateTimeTextView = item.incomingMessageDateTimeTextView
        val outgoingMessageTextView = item.outgoingMessageTextView
        val outgoingMessageDateTimeTextView = item.outgoingMessageDateTimeTextView
    }

    private inner class RecyclerViewAdapter(
        private val viewModel: ModelChatRoomViewModel
    ): RecyclerView.Adapter<RecyclerViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.view_model_chat_room_list_item, parent, false)
            return RecyclerViewHolder(view)
        }

        override fun getItemCount(): Int {
            return viewModel.chatMessages.value?.size ?: 0
        }

        override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
            viewModel.chatMessages.value?.get(position)?.let { chatMessage ->
                if (chatMessage.isIncoming) {
                    // TODO: パスを一か所に定義
                    //Picasso.get().load("https://1.bp.blogspot.com/-kwMHBpDRC98/WMfCOCDhmCI/AAAAAAABClk/0YhKPlx69H8akEluJniMmVV-RoJCRtPvACLcB/s800/onsei_ninshiki_smartphone.png").into(holder.iconImageView)
                    Picasso.get()
                        .load("http://192.168.8.190:8080" + args.hairdresser.profileImagePath)
                        .into(holder.iconImageView)
                    holder.iconImageView.visibility = View.VISIBLE
                    holder.incomingMessageTextView.text = chatMessage.content
                    holder.incomingMessageTextView.visibility = View.VISIBLE
                    holder.incomingMessageDateTimeTextView.text = chatMessage.createdAt
                    holder.incomingMessageDateTimeTextView.visibility = View.VISIBLE
                    holder.outgoingMessageTextView.visibility = View.GONE
                    holder.outgoingMessageDateTimeTextView.visibility = View.GONE
                } else {
                    holder.iconImageView.visibility = View.GONE
                    holder.outgoingMessageTextView.text = chatMessage.content
                    holder.outgoingMessageTextView.visibility = View.VISIBLE
                    holder.outgoingMessageDateTimeTextView.text = chatMessage.createdAt
                    holder.outgoingMessageDateTimeTextView.visibility = View.VISIBLE
                    holder.incomingMessageTextView.visibility = View.GONE
                    holder.incomingMessageDateTimeTextView.visibility = View.GONE
                }
            }
        }

    }

    private inner class SendButtonClickListener: View.OnClickListener {

        override fun onClick(v: View?) {
            ensureNotNull(model?.userId, args.hairdresser.userId) { myUserId, partnerUserId ->
                val message = messageEditText.text.toString()
                viewModel.sendMessage(message, myUserId, partnerUserId)
                messageEditText.setText("")
            }
        }
    }

    override val viewModel: ModelChatRoomViewModel by inject()

    private val authManager: AuthManager by inject()
    private val args: ModelChatRoomFragmentArgs by navArgs()
    private val recyclerViewAdapter = RecyclerViewAdapter(viewModel)
    private val model = authManager.currentModel()

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
    ): FragmentModelChatRoomBinding = FragmentModelChatRoomBinding.inflate(inflater, container, false)

    override fun setupViewModel(viewModel: ModelChatRoomViewModel) {
        viewModel.eventDispatcher.bind(viewLifecycleOwner, this)
        args.hairdresser.userId?.let { userId ->
            viewModel.start(args.chatRoomId, userId)
        }
    }

    override fun setupDataBinding(
        dataBinding: FragmentModelChatRoomBinding,
        savedInstanceState: Bundle?
    ) {
        dataBinding.viewModel = viewModel
    }

    override fun onMessagesChanged() {
        recyclerViewAdapter.notifyDataSetChanged()
        view?.chatRoomRecyclerView?.smoothScrollToPosition(recyclerViewAdapter.itemCount)
    }

    override fun onMessageReceived(message: String?, isIncoming: Boolean) {
        recyclerViewAdapter.notifyDataSetChanged()
        view?.chatRoomRecyclerView?.smoothScrollToPosition(recyclerViewAdapter.itemCount)
    }


}