package training20.tcmobile

import android.app.Application

class ApplicationCMM: Application() {

    override fun onCreate() {
        super.onCreate()
        ApplicationContext.onCreateApplication(applicationContext)

        //
        Debugger.debug(Role.HAIRDRESSER, "tNclJwnXL8WFA5iefUPGKoVSYv5UuYh9JVFPLyavWiJCqPfMtZdJp5iJZk70")
        //
    }
}