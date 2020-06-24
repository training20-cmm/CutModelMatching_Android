package training20.tcmobile.fragments

import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.databinding.ViewDataBinding
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.fragment_hairdresser_home.view.*
import training20.tcmobile.R
import training20.tcmobile.mvvm.MvvmFragment
import training20.tcmobile.mvvm.actions.Action
import training20.tcmobile.mvvm.viewmodels.HairdresserNavigationDrawerViewModel

abstract class HairdresserNavigationDrawerFragment<A: Action, V: ViewDataBinding, VM: HairdresserNavigationDrawerViewModel<A>>:
    MvvmFragment<V, VM>(),
    NavigationView.OnNavigationItemSelectedListener
{

    protected fun setupNavigationDrawer(toolbar: Toolbar, drawerLayout: DrawerLayout, navigationView: NavigationView) {
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
            navigationView.setNavigationItemSelectedListener(this)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.navigationDrawerMenuMySalon -> onMySalonMenuItemSelected()
        }
        return true
    }

    private fun onMySalonMenuItemSelected() {
        val actionId = if (viewModel.salon.value == null) R.id.action_hairdresserFoundationFragment_to_hairdresserSalonUnregisteredFragment else R.id.action_hairdresserFoundationFragment_to_hairdresserSalonFragment
        findNavController().navigate(actionId)
    }

}