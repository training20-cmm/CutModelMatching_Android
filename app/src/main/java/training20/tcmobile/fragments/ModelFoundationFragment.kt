package training20.tcmobile.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_hairdresser_foundation.*
import kotlinx.android.synthetic.main.fragment_hairdresser_foundation.view.*
import org.koin.android.ext.android.inject
import training20.tcmobile.R
import training20.tcmobile.databinding.FragmentModelFoundationBinding
import training20.tcmobile.mvvm.MvvmFragment
import training20.tcmobile.mvvm.actions.ModelFoundationActions
import training20.tcmobile.mvvm.viewmodels.ModelFoundationViewModel
import training20.tcmobile.ui.recyclerview.adapters.HairdresserFoundationRecyclerViewAdapter

class ModelFoundationFragment:
    MvvmFragment<FragmentModelFoundationBinding, ModelFoundationViewModel>(),
    ModelFoundationActions
{

    companion object {
        private const val HOME_ID = 0
        private const val SEARCH_RESULT_ID = 1
        private const val NOTIFICATIONS_ID = 2
        private const val CHAT_HISTORY_ID = 3

        fun newInstance(): ModelFoundationFragment {
            return ModelFoundationFragment()
        }
    }

    override val viewModel: ModelFoundationViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState) ?: return null
        setupFragmentViewPager(view)
        setupBottomNavigationView(view)
        return view
    }

    override fun createDataBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentModelFoundationBinding = FragmentModelFoundationBinding.inflate(inflater, container, false)

    override fun setupViewModel(viewModel: ModelFoundationViewModel) {
        viewModel.eventDispatcher.bind(viewLifecycleOwner, this)
    }

    override fun setupDataBinding(
        dataBinding: FragmentModelFoundationBinding,
        savedInstanceState: Bundle?
    ) {
        dataBinding.viewModel = viewModel
    }

    override fun showHome() {
        fragmentViewPager.setCurrentItem(HOME_ID, false)
    }

    override fun showSearchResult() {
        fragmentViewPager.setCurrentItem(SEARCH_RESULT_ID, false)
    }

    override fun showNotifications() {
        fragmentViewPager.setCurrentItem(NOTIFICATIONS_ID, false)
    }

    override fun showChatHistory() {
        fragmentViewPager.setCurrentItem(CHAT_HISTORY_ID, false)
    }

    private fun setupFragmentViewPager(view: View) {
        val fragments = arrayOf(
            ModelHomeFragment.newInstance(),
            ModelSearchResultFragment.newInstance(),
            ModelNotificationsFragment.newInstance(),
            ModelChatHistoryFragment.newInstance()
        )
        val fragmentViewPager = view.fragmentViewPager
        fragmentViewPager.adapter = HairdresserFoundationRecyclerViewAdapter(fragments,  childFragmentManager)
        fragmentViewPager.offscreenPageLimit = 2
    }

    private fun setupBottomNavigationView(view: View) {
        val bottomNavigationView = view.bottomNavigationView
        bottomNavigationView.setOnNavigationItemSelectedListener(this::onNavigationItemSelected)
    }

    private fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.navigationHome -> {
                showHome()
                return true
            }
            R.id.navigationSearch -> {
                showSearchResult()
                return true
            }
            R.id.navigationNotifications -> {
                showNotifications()
                return true
            }
            R.id.navigationChatHistory -> {
                showChatHistory()
                return true
            }
        }
        return false
    }
}