package training20.tcmobile.fragments

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import training20.tcmobile.R

class FlexboxListAdapter : RecyclerView.Adapter<FlexboxListViewHolder>(){
    private val items = ArrayList<FlexboxListItem>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlexboxListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_model_list_item,parent,false)

        return FlexboxListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: FlexboxListViewHolder, position: Int) {
        val item = items[position]
        holder.itemName?.text = item.name
    }

    fun setItems(items: List<FlexboxListItem>){
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }


}
