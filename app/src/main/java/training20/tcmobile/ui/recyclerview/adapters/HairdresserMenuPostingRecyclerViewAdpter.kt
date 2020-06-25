package training20.tcmobile.ui.recyclerview.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import training20.tcmobile.R
import training20.tcmobile.mvvm.viewmodels.HairdresserMenuPostingViewModel
import training20.tcmobile.ui.recyclerview.viewholders.HairdresserMenuPostingViewHolder

class HairdresserMenuPostingRecyclerViewAdpter(
    private val hairdresserMenuPostingViewModel: HairdresserMenuPostingViewModel
): RecyclerView.Adapter<HairdresserMenuPostingViewHolder>() {
    // レイアウトマネージャーが起動して、新しいviewの作成をするときに使う
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HairdresserMenuPostingViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_hairdresser_menu_items, parent, false)
        return HairdresserMenuPostingViewHolder(view)
    }


    override fun getItemCount(): Int {
        return 1
    }

    // 部品に表示する部分
    override fun onBindViewHolder(holder: HairdresserMenuPostingViewHolder, position: Int) {
        holder.checkboxText?.text = "テスト" // 表示させるところ
    }

}