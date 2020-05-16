package training20.tcmobile.notification

object NotificationCenter {

    private val observerMap: MutableMap<NotificationType, MutableList<(Notification) -> Unit>> = mutableMapOf()

    fun addObserver(type: NotificationType, observer: (Notification) -> Unit) {
        var list = observerMap[type]
        if (list == null) {
            list = mutableListOf()
            observerMap[type] = list
        }
        list.add(observer)
    }

    fun notify(type: NotificationType, notification: Notification) {
        val observers = observerMap[type]
        observers?.forEach { it(notification) }
    }

    fun removeObserver(type: NotificationType, observer: (Notification) -> Unit) {
        val list = observerMap[type]
        println(list?.removeIf {it == observer})
    }

}