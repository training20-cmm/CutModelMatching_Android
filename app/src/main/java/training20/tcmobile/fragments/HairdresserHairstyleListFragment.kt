package training20.tcmobile.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.koin.android.ext.android.inject

import training20.tcmobile.R
import training20.tcmobile.databinding.FragmentHairdresserHairstyleListBinding
import training20.tcmobile.databinding.FragmentHairdresserHairstyleListBindingImpl
import training20.tcmobile.mvvm.MvvmFragment
import training20.tcmobile.mvvm.actions.HairdresserHairstyleListActions
import training20.tcmobile.mvvm.viewmodels.HairdresserHairstyleListViewModel
import training20.tcmobile.ui.recyclerview.adapters.HairdresserHairstyleListRecyclerViewAdapter

class HairdresserHairstyleListFragment:
    MvvmFragment<FragmentHairdresserHairstyleListBinding, HairdresserHairstyleListViewModel>(),
    HairdresserHairstyleListActions
{

    override val viewModel: HairdresserHairstyleListViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState) ?: return null
        setupViews(view)
        return view
    }

    override fun createDataBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHairdresserHairstyleListBinding = FragmentHairdresserHairstyleListBinding.inflate(inflater, container, false)

    override fun setupViewModel(viewModel: HairdresserHairstyleListViewModel) {
        viewModel.eventDispatcher.bind(viewLifecycleOwner, this)
    }

    override fun setupDataBinding(
        dataBinding: FragmentHairdresserHairstyleListBinding,
        savedInstanceState: Bundle?
    ) {
        dataBinding.viewModel = viewModel
    }

    private fun setupViews(view: View) {
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = HairdresserHairstyleListRecyclerViewAdapter()
    }

}
