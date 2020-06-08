package training20.tcmobile.ui.recyclerview.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import training20.tcmobile.R
import training20.tcmobile.fragments.HairdresserHairstyleListFragment
import training20.tcmobile.ui.recyclerview.viewholders.HairdresserHairstyleListRecyclerViewHolder

class HairdresserHairstyleListRecyclerViewAdapter:
    RecyclerView.Adapter<HairdresserHairstyleListRecyclerViewHolder>() {

    override fun getItemCount(): Int {
        return 50
    }

    override fun onBindViewHolder(holder: HairdresserHairstyleListRecyclerViewHolder, position: Int) {
//            holder.textView.text = "DUMMY TEXT"
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HairdresserHairstyleListRecyclerViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.view_hairdresser_hair_catalog_list_item, parent, false)
        return HairdresserHairstyleListRecyclerViewHolder(
            itemView
        )
    }

}