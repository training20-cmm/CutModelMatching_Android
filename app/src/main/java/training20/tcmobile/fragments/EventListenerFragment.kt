package training20.tcmobile.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import org.koin.android.ext.android.inject
import training20.tcmobile.mvvm.MvvmFragment
import training20.tcmobile.mvvm.actions.Action
import training20.tcmobile.mvvm.event.EventDispatcher

abstract class EventListenerFragment<A: Action, V: ViewDataBinding, VM: ViewModel>: MvvmFragment<V, VM>() {

    protected val eventDispatcher: EventDispatcher<A> by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setupEventDispatcher()
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    abstract fun setupEventDispatcher()
}