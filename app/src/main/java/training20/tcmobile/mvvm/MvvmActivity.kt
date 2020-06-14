package training20.tcmobile.mvvm

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import training20.tcmobile.activities.BaseActivity

abstract class MvvmActivity<V: ViewDataBinding, VM: ViewModel>: BaseActivity() {

    private var dataBinding: V? = null
    protected abstract val viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = createDataBinding()
        dataBinding!!.lifecycleOwner = this
        setupViewModel(viewModel)
        setupDataBinding(dataBinding!!, savedInstanceState)
    }

    protected abstract fun createDataBinding(): V
    protected abstract fun setupViewModel(viewModel: VM)
    protected abstract fun setupDataBinding(dataBinding: V, savedInstanceState: Bundle?)
}