package training20.tcmobile.ui.recyclerview.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import training20.tcmobile.R
import training20.tcmobile.mvvm.viewmodels.HairdresserMenuPostingViewModel
import training20.tcmobile.mvvm.viewmodels.response_sample
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
        return hairdresserMenuPostingViewModel.response.size
    }

    // 部品に表示する部分
    override fun onBindViewHolder(holder: HairdresserMenuPostingViewHolder, position: Int) {
        val item = hairdresserMenuPostingViewModel.response[position]
        println(item)
        holder.checkboxText.text = item.name // 表示させるところ

        // ここでチェックボックスにイベントリスナーを付ける
        holder.checkbox.setOnClickListener {
            // 具体的な処理はviewmodelに
            hairdresserMenuPostingViewModel.itemcheck(item.id)
        }
    }



}