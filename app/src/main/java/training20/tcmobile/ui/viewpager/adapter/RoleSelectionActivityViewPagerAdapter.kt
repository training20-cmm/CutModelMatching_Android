package training20.tcmobile.ui.viewpager.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import training20.tcmobile.ApplicationContext
import training20.tcmobile.R
import training20.tcmobile.fragments.RoleSelectionHairdresserCardFragment
import training20.tcmobile.fragments.RoleSelectionModelCardFragment

class RoleSelectionActivityViewPagerAdapter(fragmentManager: FragmentManager):
    FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT)
{
    override fun getCount(): Int {
        return 2
    }

    override fun getItem(position: Int): Fragment {
        return when(position) {
            0 -> RoleSelectionModelCardFragment()
            else -> RoleSelectionHairdresserCardFragment()
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position) {
            0 -> ApplicationContext.context.getString(R.string.activity_role_selection_tab_item_title_model)
            else -> ApplicationContext.context.getString(R.string.activity_role_selection_tab_item_title_hairdresser)
        }
    }
}