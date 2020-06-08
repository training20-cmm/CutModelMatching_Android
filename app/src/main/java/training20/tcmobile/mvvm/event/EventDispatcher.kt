package training20.tcmobile.mvvm.event

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent
import java.util.concurrent.Executor

class EventDispatcher<ListenerType: Any> {

    private val executor: Executor
    val pendingEvents = mutableListOf<ListenerType.() -> Unit>()
    private var currentListener: ListenerType? = null

    constructor(): this(ExecutorHelper.createExecutorOnMainLooper())
    constructor(executor: Executor) {
        this.executor = executor
    }

    fun bind(lifecycleOwner: LifecycleOwner, listener: ListenerType) {
        val observer = object: LifecycleObserver {

            @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
            fun enableListener() {
                currentListener = listener
                executor.execute {
                    pendingEvents.forEach { it(listener) }
                    pendingEvents.clear()
                }
            }

            @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
            fun disableListener() {
                currentListener = null
            }

            @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
            fun removeObserver(source: LifecycleOwner) {
                source.lifecycle.removeObserver(this)
            }

        }
        lifecycleOwner.lifecycle.addObserver(observer)
    }

    fun dispatchEvent(event: ListenerType.() -> Unit) {
        val listener = currentListener
        if (listener == null) {
            executor.execute { pendingEvents.add(event) }
        } else {
            executor.execute { event(listener) }
        }
    }
}