package training20.tcmobile.fragments.hairdresser

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import training20.tcmobile.R

class HairCatalogListFragment : Fragment() {

    private class HairCatalogListItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }


    private class HairCatalogRecyclerViewAdapter:
        RecyclerView.Adapter<HairCatalogListItemViewHolder>() {

        override fun getItemCount(): Int {
            return 50
        }

        override fun onBindViewHolder(holder: HairCatalogListItemViewHolder, position: Int) {
//            holder.textView.text = "DUMMY TEXT"
        }

        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): HairCatalogListItemViewHolder {
            val itemView = LayoutInflater.from(parent.context).inflate(R.layout.hairdresser_fragment_hair_catalog_list_item, parent, false)
            return HairCatalogListItemViewHolder(itemView)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.hairdresser_fragment_hair_catalog_list, container, false)
        setupViews(rootView)
        return rootView
    }

    private fun setupViews(rootView: View) {
        val recyclerView = rootView.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = HairCatalogRecyclerViewAdapter()
    }

}
