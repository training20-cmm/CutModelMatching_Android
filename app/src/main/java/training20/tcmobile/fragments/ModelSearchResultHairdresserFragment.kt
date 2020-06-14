package training20.tcmobile.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_model_search_result_hairdresser.view.*
import kotlinx.android.synthetic.main.view_model_search_result_hairdresser_card.view.*

import training20.tcmobile.R
import training20.tcmobile.mvvm.views.ModelSearchResultHairdresserMenuCardView

class ModelSearchResultHairdresserFragment : Fragment() {

    private  class RecyclerViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val menuCardsLinearLayout: LinearLayout = itemView.menuCardsLinearLayout
    }

    private inner class RecyclerViewAdapter: RecyclerView.Adapter<RecyclerViewHolder>() {

        private var parent: ViewGroup? = null

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
            this.parent = parent
            val inflater = LayoutInflater.from(parent.context)
            val view = inflater.inflate(R.layout.view_model_search_result_hairdresser_card, parent, false)
            return RecyclerViewHolder(view)
        }

        override fun getItemCount(): Int {
            return 10
//            return hairdressers.size
        }

        override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
            holder.menuCardsLinearLayout.addView(ModelSearchResultHairdresserMenuCardView(
                activity!!
            ))
            holder.menuCardsLinearLayout.addView(ModelSearchResultHairdresserMenuCardView(
                activity!!
            ))
            holder.menuCardsLinearLayout.addView(ModelSearchResultHairdresserMenuCardView(
                activity!!
            ))
            holder.menuCardsLinearLayout.addView(ModelSearchResultHairdresserMenuCardView(
                activity!!
            ))
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_model_search_result_hairdresser, container, false)
        view.recyclerView.adapter = RecyclerViewAdapter()
        view.recyclerView.layoutManager = LinearLayoutManager(activity)
        return view
    }
}
