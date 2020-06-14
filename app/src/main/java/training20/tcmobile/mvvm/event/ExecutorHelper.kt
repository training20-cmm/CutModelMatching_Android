package training20.tcmobile.mvvm.event

import android.os.Handler
import android.os.Looper
import java.util.concurrent.Executor

object ExecutorHelper {

    fun createExecutorOnMainLooper(): Executor {
        val mainLooper = Looper.getMainLooper()
        val mainHandler = Handler(mainLooper)
        return Executor {
            mainHandler.post(it)
        }
    }
}