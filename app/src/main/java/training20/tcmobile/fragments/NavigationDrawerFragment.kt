package training20.tcmobile.fragments

import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.databinding.ViewDataBinding
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import kotlinx.android.synthetic.main.fragment_hairdresser_home.view.*
import training20.tcmobile.R
import training20.tcmobile.mvvm.MvvmFragment

abstract class NavigationDrawerFragment<V: ViewDataBinding, VM: ViewModel>: MvvmFragment<V, VM>() {

    protected fun setupNavigationDrawer(toolbar: Toolbar, drawerLayout: DrawerLayout) {
        val appCompatActivity  = activity as? AppCompatActivity
        appCompatActivity?.let {
            appCompatActivity.setSupportActionBar(toolbar)
            appCompatActivity.actionBar?.setHomeButtonEnabled(true)
            appCompatActivity.actionBar?.setDisplayHomeAsUpEnabled(false)
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
        }
    }
}