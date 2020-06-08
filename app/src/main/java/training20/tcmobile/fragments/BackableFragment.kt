package training20.tcmobile.fragments

import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.databinding.ViewDataBinding
import androidx.navigation.fragment.findNavController
import training20.tcmobile.mvvm.MvvmFragment
import training20.tcmobile.mvvm.actions.BackNavigation
import training20.tcmobile.mvvm.viewmodels.BackableViewModel

abstract class BackableFragment<A: BackNavigation, V: ViewDataBinding, VM: BackableViewModel<A>> : MvvmFragment<V, VM>(), BackNavigation
{

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object:
//            OnBackPressedCallback(true) {
//            override fun handleOnBackPressed() {
//                viewModel.onBack()
//            }
//        })
//    }

    override fun back() {
        findNavController().navigateUp()
    }
}