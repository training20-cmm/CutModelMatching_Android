package training20.tcmobile.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import kotlinx.android.synthetic.main.fragment_hairdresser_home.view.*
import training20.tcmobile.R

class HairdresserHomeFragment : Fragment() {


    private inner class TabAdapter(fragmentManager: FragmentManager):
        FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT)
    {
        override fun getCount(): Int {
            return 2
        }

        override fun getItem(position: Int): Fragment {
            return when(position) {
                0 -> HairdresserHairCatalogListFragment()
                else -> HairdresserHairCatalogListFragment()
            }
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return when(position) {
                0 -> getString(R.string.activity_hairdresser_home_tab_item_title_hair_catalog)
                else -> getString(R.string.activity_hairdresser_home_tab_item_title_blog)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_hairdresser_home, container, false)
        fragmentManager?.let { fragmentManager ->
            view.viewPager.adapter = TabAdapter(fragmentManager)
            view.tabLayout.setupWithViewPager(view.viewPager)
        }
        return view
    }

//    private fun startFeatureDiscovery() {
//        val tabLayoutViewGroup = tabLayout.getChildAt(0) as ViewGroup
//        val tabItemHairCatalog = tabLayoutViewGroup.getChildAt(0)
//        val tabItemBlog = tabLayoutViewGroup.getChildAt(1)
//        val sequence = TapTargetSequence(this)
//        sequence.targets(
//            TapTarget.forToolbarNavigationIcon(toolbar,
//                getString(R.string.activity_hairdresser_home_feature_discovery_title_toolbar_navigation_button),
//                getString(R.string.activity_hairdresser_home_feature_discovery_description_toolbar_navigation_button)
//            )
//                .cancelable(false)
//                .tintTarget(false)
//                .id(1),
//            TapTarget.forView(
//                editProfileButton,
//                getString(R.string.activity_hairdresser_home_feature_discovery_title_edit_profile_button),
//                getString(R.string.activity_hairdresser_home_feature_discovery_description_edit_profile_button)
//            )
//                .cancelable(false)
//                .tintTarget(false)
//                .id(2),
//            TapTarget.forView(
//                tabItemHairCatalog,
//                getString(R.string.activity_hairdresser_home_feature_discovery_title_tab_item_hair_catalog),
//                getString(R.string.activity_hairdresser_home_feature_discovery_description_tab_item_hair_catalog)
//            )
//                .cancelable(false)
//                .id(3),
//            TapTarget.forView(
//                tabItemBlog,
//                getString(R.string.activity_hairdresser_home_feature_discovery_title_tab_item_blog),
//                getString(R.string.activity_hairdresser_home_feature_discovery_description_tab_item_blog)
//            )
//                .cancelable(false)
//                .id(4),
//            TapTarget.forView(
//                fabSpeedDial,
//                getString(R.string.activity_hairdresser_home_feature_discovery_title_fab_speed_dialog),
//                getString(R.string.activity_hairdresser_home_feature_discovery_description_fab_speed_dialog)
//            )
//                .tintTarget(false)
//                .id(5)
//        ).start()
//    }
//

}
