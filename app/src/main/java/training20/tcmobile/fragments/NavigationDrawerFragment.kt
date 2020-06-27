package training20.tcmobile.fragments

import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.databinding.ViewDataBinding
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModel
import com.google.android.material.navigation.NavigationView
import training20.tcmobile.R
import training20.tcmobile.mvvm.MvvmFragment

abstract class NavigationDrawerFragment<V: ViewDataBinding, VM: ViewModel>: MvvmFragment<V, VM>() {

    protected fun setupNavigationDrawer(
        toolbar: Toolbar,
        drawerLayout: DrawerLayout,
        navigationView: NavigationView,
        navigationItemSelectedListener: NavigationView.OnNavigationItemSelectedListener
    ) {
        val appCompatActivity  = activity as? AppCompatActivity
        appCompatActivity?.let {
            appCompatActivity.setSupportActionBar(toolbar)
            appCompatActivity.actionBar?.setHomeButtonEnabled(true)
            appCompatActivity.actionBar?.setDisplayHomeAsUpEnabled(false)
            appCompatActivity.title = " "
            val toggle = ActionBarDrawerToggle(
                appCompatActivity,
                drawerLayout,
                toolbar,
                R.string.menu_open,
                R.string.menu_close
            )
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
            navigationView.setNavigationItemSelectedListener(navigationItemSelectedListener)
        }
    }
}