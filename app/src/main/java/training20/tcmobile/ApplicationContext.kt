package training20.tcmobile

import android.content.Context

object ApplicationContext {

    lateinit var context: Context
        private set

    fun onCreateApplication(applicationContext: Context) {
        context = applicationContext
    }
}