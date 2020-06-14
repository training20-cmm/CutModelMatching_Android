package training20.tcmobile.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import kotlinx.android.synthetic.main.fragment_model_search_result.view.*
import training20.tcmobile.R

class ModelSearchResultFragment: Fragment() {

    private inner class PagerAdapter(fragmentManager: FragmentManager): FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
        override fun getItem(position: Int): Fragment {
            return when(position) {
                0 -> ModelSearchResultHairdresserFragment()
                else -> ModelSearchResultMenuFragment()
            }
        }

        override fun getCount(): Int {
            return 2
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return when(position) {
                0 -> getString(R.string.activity_model_search_result_tab_item_title_hairdresser)
                else -> getString(R.string.activity_model_search_result_tab_item_title_menu)
            }
        }

    }

    companion object {
        fun newInstance(): ModelSearchResultFragment {
            return ModelSearchResultFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_model_search_result, container, false)
        view.viewPager.adapter = PagerAdapter(fragmentManager!!)
        return  view
    }
}