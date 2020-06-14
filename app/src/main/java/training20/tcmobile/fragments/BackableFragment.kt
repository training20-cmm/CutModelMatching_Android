package training20.tcmobile.fragments

import androidx.databinding.ViewDataBinding
import androidx.navigation.fragment.findNavController
import training20.tcmobile.mvvm.MvvmFragment
import training20.tcmobile.mvvm.actions.BackAction
import training20.tcmobile.mvvm.viewmodels.BackableViewModel

abstract class BackableFragment<A: BackAction, V: ViewDataBinding, VM: BackableViewModel<A>> : MvvmFragment<V, VM>(), BackAction
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