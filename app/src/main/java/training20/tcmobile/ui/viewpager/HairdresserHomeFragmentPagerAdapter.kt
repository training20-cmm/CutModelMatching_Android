package training20.tcmobile.ui.viewpager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import training20.tcmobile.ApplicationContext
import training20.tcmobile.R
import training20.tcmobile.fragments.HairdresserHairstyleListFragment

class HairdresserHomeFragmentPagerAdapter(fragmentManager: FragmentManager):
    FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT)
{
    override fun getCount(): Int {
        return 2
    }

    override fun getItem(position: Int): Fragment {
        return when(position) {
            0 -> HairdresserHairstyleListFragment()
            else -> HairdresserHairstyleListFragment()
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position) {
            0 -> ApplicationContext.context.getString(R.string.activity_hairdresser_home_tab_item_title_hair_catalog)
            else -> ApplicationContext.context.getString(R.string.activity_hairdresser_home_tab_item_title_blog)
        }
    }
}