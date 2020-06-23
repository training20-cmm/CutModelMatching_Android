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
import io.realm.Realm
import kotlinx.android.synthetic.main.fragment_model_chat_room.*
import kotlinx.android.synthetic.main.fragment_model_chat_room.view.*
import kotlinx.android.synthetic.main.fragment_model_chat_room.view.messageEditText
import kotlinx.android.synthetic.main.view_model_chat_room_list_item.view.*
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
import java.net.URI

class ModelChatRoomFragment :
    BackableFragment<ModelChatRoomActions, FragmentModelChatRoomBinding, ModelChatRoomViewModel>(),
    ModelChatRoomActions
{

    private class RecyclerViewHolder(item: View): RecyclerView.ViewHolder(item) {
        val messageTextView = item.messageTextView
    }

    private class RecyclerViewAdapter(
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
            holder.messageTextView.text = viewModel.chatMessages.value?.get(position)?.content
        }

    }

    private inner class SendButtonClickListener: View.OnClickListener {

        override fun onClick(v: View?) {
            val jsonObject = JSONObject()
            jsonObject.put("text", messageEditText.text.toString())
            jsonObject.put("myUserId", authManager.currentModel()?.userId)
            jsonObject.put("partnerUserId", args.hairdresser.userId)
            println(jsonObject.toString())
            viewModel.sendMessage(jsonObject.toString())
            messageEditText.setText("")
        }
    }

    override val viewModel: ModelChatRoomViewModel by inject()

    private val authManager: AuthManager by inject()
    private val args: ModelChatRoomFragmentArgs by navArgs()
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

    override fun onChatMessagesChanged() {
        recyclerViewAdapter.notifyDataSetChanged()
    }

    override fun onMessageReceived(message: String?) {
        recyclerViewAdapter.notifyDataSetChanged()
    }


}