package training20.tcmobile.activities

import android.os.Bundle
import android.view.ViewGroup
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.getkeepsafe.taptargetview.TapTarget
import com.getkeepsafe.taptargetview.TapTargetSequence
import kotlinx.android.synthetic.main.activity_hairdresser_home.*
import kotlinx.android.synthetic.main.activity_hairdresser_home.pager
import kotlinx.android.synthetic.main.activity_hairdresser_home.tabLayout
import training20.tcmobile.R
import training20.tcmobile.fragments.HairdresserHairCatalogListFragment


class HairdresserHomeActivity : BaseActivity() {

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hairdresser_home)
        setupNavigationDrawer()
        setupViews()
        startFeatureDiscovery()
    }

    private fun startFeatureDiscovery() {
        val tabLayoutViewGroup = tabLayout.getChildAt(0) as ViewGroup
        val tabItemHairCatalog = tabLayoutViewGroup.getChildAt(0)
        val tabItemBlog = tabLayoutViewGroup.getChildAt(1)
        val sequence = TapTargetSequence(this)
        sequence.targets(
            TapTarget.forToolbarNavigationIcon(toolbar,
                getString(R.string.activity_hairdresser_home_feature_discovery_title_toolbar_navigation_button),
                getString(R.string.activity_hairdresser_home_feature_discovery_description_toolbar_navigation_button)
            )
                .cancelable(false)
                .tintTarget(false)
                .id(1),
            TapTarget.forView(
                editProfileButton,
                getString(R.string.activity_hairdresser_home_feature_discovery_title_edit_profile_button),
                getString(R.string.activity_hairdresser_home_feature_discovery_description_edit_profile_button)
            )
                .cancelable(false)
                .tintTarget(false)
                .id(2),
            TapTarget.forView(
                tabItemHairCatalog,
                getString(R.string.activity_hairdresser_home_feature_discovery_title_tab_item_hair_catalog),
                getString(R.string.activity_hairdresser_home_feature_discovery_description_tab_item_hair_catalog)
            )
                .cancelable(false)
                .id(3),
            TapTarget.forView(
                tabItemBlog,
                getString(R.string.activity_hairdresser_home_feature_discovery_title_tab_item_blog),
                getString(R.string.activity_hairdresser_home_feature_discovery_description_tab_item_blog)
            )
                .cancelable(false)
                .id(4),
            TapTarget.forView(
                fabSpeedDial,
                getString(R.string.activity_hairdresser_home_feature_discovery_title_fab_speed_dialog),
                getString(R.string.activity_hairdresser_home_feature_discovery_description_fab_speed_dialog)
            )
                .tintTarget(false)
                .id(5)
        ).start()
    }

    private fun setupNavigationDrawer() {
        setSupportActionBar(toolbar)
        actionBar?.setHomeButtonEnabled(true)
        actionBar?.setDisplayHomeAsUpEnabled(false)
        val toggle = ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.menu_open, R.string.menu_close)
        toggle.isDrawerIndicatorEnabled = false
        toggle.setHomeAsUpIndicator(R.drawable.menu_32dp)
        toggle.setToolbarNavigationClickListener {
            if (drawerLayout.isDrawerVisible(GravityCompat.START)) {
                drawerLayout.closeDrawer(GravityCompat.START)
            } else {
                drawerLayout.openDrawer(GravityCompat.START)
            }
        }
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
    }

    private fun setupViews() {
        pager.adapter = TabAdapter(supportFragmentManager)
        tabLayout.setupWithViewPager(pager)
    }
}
