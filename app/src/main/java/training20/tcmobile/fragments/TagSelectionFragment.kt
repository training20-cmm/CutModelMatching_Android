package training20.tcmobile.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import kotlinx.android.synthetic.main.fragment_tag_list.*
import training20.tcmobile.R
import training20.tcmobile.Tag

class TagSelectionFragment : Fragment() {

    class Tab(val title: String, val originalTags: MutableList<Tag>) {
        val tags = originalTags.map{it}.toMutableList()

        fun remove(tag: Tag) {
            originalTags.remove(tag)
            tags.remove(tag)
        }
    }

    class Adapter(
        fragmentManager: FragmentManager,
        private val tabs: ArrayList<Tab>,
        private val listItemClickListener: (Tag) -> Unit
    ): FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

        private val fragments = tabs.map { tab ->
            TagListFragment.newInstance(tab.tags, this::onListItemClick)
        }

        override fun getItem(position: Int): Fragment {
            return fragments[position]
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return tabs[position].title
        }

        override fun getCount(): Int {
            return tabs.size
        }

        fun filter(str: String?) {
            tabs.forEach { tab ->
                tab.tags.clear()
                str?.let { str ->
                    tab.originalTags.forEach { tag ->
                        if (tag.name.contains(str)) {
                            tab.tags.add(tag)
                        }
                    }
                }
            }
            notifyChanges()
        }

        private fun notifyChanges() {
            fragments.forEach { fragment ->
                if (fragment.view != null) {
                    fragment.tagListView?.adapter?.let {(it as ArrayAdapter<*>).notifyDataSetChanged()}
                }
            }
        }

        private fun onListItemClick(tag: Tag) {
            tabs.forEach { it.remove(tag) }
            notifyChanges()
            listItemClickListener(tag)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_tag_selection, container, false)
    }

}