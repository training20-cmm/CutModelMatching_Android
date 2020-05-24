package training20.tcmobile.activities

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_hairdresser_foundation.*
import training20.tcmobile.R
import training20.tcmobile.fragments.HairdresserHomeFragment
import training20.tcmobile.fragments.ModelHomeFragment

class HairdresserFoundationActivity: BaseActivity() {

    private class FragmentViewPagerAdapter(
        val fragments: Array<Fragment>,
        fragmentManager: FragmentManager
    ): FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

        override fun getItem(position: Int): Fragment {
            return fragments[position]
        }

        override fun getCount() = fragments.size

    }

    private inner class BottomNavigationViewOnNavigationItemSelectedListener: BottomNavigationView.OnNavigationItemSelectedListener {
        override fun onNavigationItemSelected(item: MenuItem): Boolean {
            return true
//            toolbarRelativeLayout.removeAllViews()
//            return when(item.itemId) {
//                R.id.navigationHome -> {
//                    fragmentViewPager.setCurrentItem(0, false)
//                    true
//                }
//                R.id.navigationSearch -> {
//                    toolbarRelativeLayout.addView(LayoutInflater.from(this@ModelFoundationActivity).inflate(R.layout.view_model_searchbar, drawerLayout, false))
//                    fragmentViewPager.setCurrentItem(1, false)
//                    true
//                }
//                R.id.navigationNotifications -> {
//                    fragmentViewPager.setCurrentItem(2, false)
//                    true
//                }
//                R.id.navigationMessages -> {
//                    fragmentViewPager.setCurrentItem(3, false)
//                    true
//                }
//                else -> false
//            }
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hairdresser_foundation)
        setupNavigationDrawer()
        setupFragmentViewPager()
        setupViews()
//        startFeatureDiscovery()
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

    private fun setupFragmentViewPager() {
        val fragments = arrayOf(HairdresserHomeFragment(), ModelHomeFragment(), HairdresserHomeFragment())
        fragmentViewPager.adapter = FragmentViewPagerAdapter(fragments, supportFragmentManager)
        fragmentViewPager.offscreenPageLimit = 2
    }

    private fun setupViews() {
        bottomNavigationView.setOnNavigationItemSelectedListener(BottomNavigationViewOnNavigationItemSelectedListener())
    }

}