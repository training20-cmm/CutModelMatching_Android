package training20.tcmobile.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import kotlinx.android.synthetic.main.activity_perspective_selection.*
import training20.tcmobile.PerspectiveManager
import training20.tcmobile.R
import training20.tcmobile.fragments.PerspectiveSelectionHairdresserCardFragment
import training20.tcmobile.fragments.PerspectiveSelectionModelCardFragment

class PerspectiveSelectionActivity : AppCompatActivity() {

    private inner class TabAdapter(fragmentManager: FragmentManager):
        FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT)
    {
        override fun getCount(): Int {
            return 2
        }

        override fun getItem(position: Int): Fragment {
            return when(position) {
                0 -> PerspectiveSelectionModelCardFragment()
                else -> PerspectiveSelectionHairdresserCardFragment()
            }
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return when(position) {
                0 -> getString(R.string.activity_perspective_selection_tab_item_title_model)
                else -> getString(R.string.activity_perspective_selection_tab_item_title_hairdresser)
            }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perspective_selection)
        setupViews()
        PerspectiveManager.setPerspective(null)
    }

    private fun setupViews() {
        viewPager.adapter = TabAdapter(supportFragmentManager)
        tabLayout.setupWithViewPager(viewPager)
    }
}
