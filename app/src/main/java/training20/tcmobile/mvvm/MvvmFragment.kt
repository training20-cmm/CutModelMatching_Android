package training20.tcmobile.mvvm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel

abstract class MvvmFragment<V: ViewDataBinding, VM: ViewModel>: Fragment() {

    private var dataBinding: V? = null
    protected abstract val viewModel: VM

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = createDataBinding(inflater, container)
        return dataBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val dataBinding = dataBinding ?: return
        dataBinding.lifecycleOwner = viewLifecycleOwner
        setupViewModel(viewModel)
        setupDataBinding(dataBinding, savedInstanceState)
    }

    protected abstract fun createDataBinding(inflater: LayoutInflater, container: ViewGroup?): V
    protected abstract fun setupViewModel(viewModel: VM)
    protected abstract fun setupDataBinding(dataBinding: V, savedInstanceState: Bundle?)
}