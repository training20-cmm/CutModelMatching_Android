package training20.tcmobile.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.google.android.flexbox.FlexboxLayoutManager
import kotlinx.android.synthetic.main.fragment_model_menu_search.view.*
import kotlinx.android.synthetic.main.view_menu_treatment_check_box.view.*
import kotlinx.android.synthetic.main.view_toolbar.view.*
import org.koin.android.ext.android.inject
import training20.tcmobile.MenuTreatmentList
import training20.tcmobile.R
import training20.tcmobile.databinding.FragmentModelMenuSearchBinding
import training20.tcmobile.databinding.ViewMenuTreatmentCheckBoxBinding
import training20.tcmobile.mvvm.actions.ModelMenuSearchActions
import training20.tcmobile.mvvm.models.MenuTreatment
import training20.tcmobile.mvvm.viewmodels.ModelMenuSearchViewModel

class ModelMenuSearchFragment :
    BackableFragment<ModelMenuSearchActions, FragmentModelMenuSearchBinding, ModelMenuSearchViewModel>(),
    ModelMenuSearchActions
{

    private class MenuTreatmentRecyclerViewHolder(val itemViewBinding: ViewMenuTreatmentCheckBoxBinding):
        RecyclerView.ViewHolder(itemViewBinding.root)
    {
        val checkBox: CheckBox = itemView.checkBox
    }

    private inner class MenuTreatmentRecyclerViewAdapter(
        private val menuTreatmentList: Array<MenuTreatment>
    ): RecyclerView.Adapter<MenuTreatmentRecyclerViewHolder>() {

        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): MenuTreatmentRecyclerViewHolder {
            val viewBinding = ViewMenuTreatmentCheckBoxBinding.inflate(layoutInflater, parent, false)
            viewBinding.viewModel = viewModel
            return MenuTreatmentRecyclerViewHolder(viewBinding)
        }

        override fun getItemCount(): Int {
            return menuTreatmentList.size
        }

        override fun onBindViewHolder(holder: MenuTreatmentRecyclerViewHolder, position: Int) {
            holder.itemViewBinding.menuTreatmentId = menuTreatmentList[position].id
            holder.checkBox.text = menuTreatmentList[position].name
        }

    }

    override val viewModel: ModelMenuSearchViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState) ?: return null
        setupToolbar(view)
        setupMenuTreatmentRecyclerView(view)
        return view
    }

    override fun createDataBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentModelMenuSearchBinding = FragmentModelMenuSearchBinding.inflate(inflater, container, false)

    override fun setupViewModel(viewModel: ModelMenuSearchViewModel) {
        viewModel.eventDispatcher.bind(viewLifecycleOwner, this)
    }

    override fun setupDataBinding(
        dataBinding: FragmentModelMenuSearchBinding,
        savedInstanceState: Bundle?
    ) {
        dataBinding.viewModel = viewModel
    }

    private fun setupToolbar(view: View) {
        view.toolbarBackButton.setOnClickListener { viewModel.onBack() }
        view.toolbarTitleTextView.text = getString(R.string.fragment_model_menu_search_toolbar_title)
    }

    private fun setupMenuTreatmentRecyclerView(view: View) {
        val layoutManager = FlexboxLayoutManager(requireActivity())
        val menuTreatmentRecyclerView = view.menuTreatmentRecyclerView
        menuTreatmentRecyclerView.adapter = MenuTreatmentRecyclerViewAdapter(MenuTreatmentList.all())
        menuTreatmentRecyclerView.layoutManager = layoutManager
    }
}