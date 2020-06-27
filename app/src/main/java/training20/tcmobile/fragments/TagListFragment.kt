package training20.tcmobile.fragments


import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.fragment_tag_list.*
import kotlinx.android.synthetic.main.fragment_tag_list.view.*
import training20.tcmobile.R

class TagListFragment: Fragment() {

    companion object {
        fun newInstance(tags: MutableList<TagSelectionFragment.Tag>, listItemClickListener: (TagSelectionFragment.Tag) -> Unit): TagListFragment {
            val tagListFragment = TagListFragment()
            tagListFragment.tags = tags
            tagListFragment.listItemClickListener = listItemClickListener
            return tagListFragment
        }
    }

    var listItemClickListener: ((TagSelectionFragment.Tag) -> Unit)? = null

    private var tags: MutableList<TagSelectionFragment.Tag>? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_tag_list, container, false)
        tags?.let { tags ->
            val adapter = activity?.applicationContext?.let { it -> ArrayAdapter(it, android.R.layout.simple_list_item_1, tags) }
            root.tagListView.adapter = adapter
            root.tagListView.setOnItemClickListener { parent, view, position, id ->
                listItemClickListener?.invoke(tags[position])
            }
        }
        return root
    }

}