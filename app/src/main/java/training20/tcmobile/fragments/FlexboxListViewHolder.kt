package training20.tcmobile.fragments

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.view_model_list_item.view.*

class FlexboxListViewHolder(view: View): RecyclerView.ViewHolder(view){
    val itemName: TextView? = view?.item_name
}