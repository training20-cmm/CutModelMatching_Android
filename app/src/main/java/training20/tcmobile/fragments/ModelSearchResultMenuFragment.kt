package training20.tcmobile.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_model_search_result_menu.view.*
import kotlinx.android.synthetic.main.view_model_search_result_menu_card.view.*

import training20.tcmobile.R
import training20.tcmobile.Tag
import training20.tcmobile.views.TagsView

class ModelSearchResultMenuFragment : Fragment() {

    private class RecyclerViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val treatmentTagsView: TagsView = itemView.treatmentTagsView
        val menuTagsView: TagsView = itemView.menuTagsView
    }

    private inner class RecyclerViewAdapter: RecyclerView.Adapter<RecyclerViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.view_model_search_result_menu_card, parent, false)
            return RecyclerViewHolder(view)
        }

        override fun getItemCount(): Int {
            return 10
        }

        override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
            holder.treatmentTagsView.set(arrayOf(
                Tag("カット", ContextCompat.getColor(activity!!, R.color.textColorSecondary), "#f0f0f0"),
                Tag("カラー", ContextCompat.getColor(activity!!, R.color.textColorSecondary), "#f0f0f0")
            ))
            holder.menuTagsView.set(arrayOf(
                Tag("メンズ", ContextCompat.getColor(activity!!, R.color.colorOnPrimary), "#9fc3e7"),
                Tag("駅近", ContextCompat.getColor(activity!!, R.color.colorOnPrimary), "#82cabc")
            ))
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_model_search_result_menu, container, false)
        view.recyclerView.adapter = RecyclerViewAdapter()
        view.recyclerView.layoutManager = LinearLayoutManager(activity)
        return view
    }

}
