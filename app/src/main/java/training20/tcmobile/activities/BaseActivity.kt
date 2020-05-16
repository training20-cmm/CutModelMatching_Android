package training20.tcmobile.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import training20.tcmobile.Perspective
import training20.tcmobile.PerspectiveManager
import training20.tcmobile.notification.Notification
import training20.tcmobile.notification.NotificationCenter
import training20.tcmobile.notification.NotificationType

open class BaseActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        NotificationCenter.addObserver(NotificationType.EXPIRED_REFRESH_TOKEN, this::handleNotification)
        NotificationCenter.addObserver(NotificationType.INVALID_REFRESH_TOKEN, this::handleNotification)
    }

    override fun onDestroy() {
        NotificationCenter.removeObserver(NotificationType.EXPIRED_REFRESH_TOKEN, this::handleNotification)
        NotificationCenter.removeObserver(NotificationType.INVALID_REFRESH_TOKEN, this::handleNotification)
        super.onDestroy()
    }

    private fun handleNotification(notification: Notification) {
        when (PerspectiveManager.current()) {
            Perspective.HAIRDRESSER -> startActivity(
                Intent(
                    this,
                    HairdresserRegistrationFormActivity::class.java
                )
            )
            Perspective.MODEL -> startActivity(
                Intent(
                    this,
                    ModelRegistrationFormActivity::class.java
                )
            )
        }
    }
}