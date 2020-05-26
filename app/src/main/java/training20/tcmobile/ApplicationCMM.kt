package training20.tcmobile

import android.app.Application

class ApplicationCMM: Application() {

    override fun onCreate() {
        super.onCreate()
        ApplicationContext.onCreateApplication(applicationContext)
    }
}