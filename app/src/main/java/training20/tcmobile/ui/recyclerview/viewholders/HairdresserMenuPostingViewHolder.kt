package training20.tcmobile.ui.recyclerview.viewholders

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.view_hairdresser_menu_items.view.*


class HairdresserMenuPostingViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
    // 表示させるものを書いていく。下で入れるのも忘れずに
    var checkboxText: TextView? = null

    init {
        checkboxText = itemView.orders
    }
}