package training20.tcmobile.activities

import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import kotlinx.android.synthetic.main.model_activity_home.*
import training20.tcmobile.R
import training20.tcmobile.activities.BaseActivity

class ModelHomeActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.model_activity_home)
        setupNavigationDrawer()
    }

    private fun setupNavigationDrawer() {
        setSupportActionBar(toolbar)
        actionBar?.setHomeButtonEnabled(true)
        actionBar?.setDisplayHomeAsUpEnabled(false)
        val drawerLayout = findViewById<DrawerLayout>(R.id.drawerLayout)
        val toggle = ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.menu_open, R.string.menu_close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
    }
}
