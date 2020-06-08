package training20.tcmobile.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_hairdresser_foundation.*
import kotlinx.android.synthetic.main.fragment_hairdresser_foundation.view.*
import kotlinx.android.synthetic.main.fragment_hairdresser_foundation.view.fragmentViewPager
import org.koin.android.ext.android.inject
import training20.tcmobile.databinding.FragmentHairdresserFoundationBinding
import training20.tcmobile.mvvm.MvvmFragment
import training20.tcmobile.mvvm.actions.HairdresserFoundationActions
import training20.tcmobile.mvvm.viewmodels.HairdresserFoundationViewModel
import training20.tcmobile.ui.recyclerview.adapters.HairdresserFoundationRecyclerViewAdapter

class HairdresserFoundationFragment:
    MvvmFragment<FragmentHairdresserFoundationBinding, HairdresserFoundationViewModel>(),
    HairdresserFoundationActions
{

    companion object {
        private const val HOME_ID = 0
        private const val NOTIFICATIONS_ID = 1
        private const val CHAT_HISTORY_ID = 2
    }

    override val viewModel: HairdresserFoundationViewModel by inject()

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
    ): FragmentHairdresserFoundationBinding = FragmentHairdresserFoundationBinding.inflate(inflater, container, false)

    override fun setupViewModel(viewModel: HairdresserFoundationViewModel) {
        viewModel.eventDispatcher.bind(viewLifecycleOwner, this)
    }

    override fun setupDataBinding(
        dataBinding: FragmentHairdresserFoundationBinding,
        savedInstanceState: Bundle?
    ) {
        dataBinding.viewModel = viewModel
    }

    override fun showHome() {
        fragmentViewPager.setCurrentItem(HOME_ID, false)
    }

    override fun showNotifications() {
        fragmentViewPager.setCurrentItem(NOTIFICATIONS_ID, false)
    }

    override fun showChatHistory() {
        fragmentViewPager.setCurrentItem(CHAT_HISTORY_ID, false)
    }

    private fun setupFragmentViewPager(view: View) {
        val fragments = arrayOf(HairdresserHomeFragment(), ModelHomeFragment(), HairdresserChatHistoryFragment.newInstance())
        val fragmentViewPager = view.fragmentViewPager
        fragmentViewPager.adapter = HairdresserFoundationRecyclerViewAdapter(fragments,  childFragmentManager)
        fragmentViewPager.offscreenPageLimit = 2
    }

    private fun setupBottomNavigationView(view: View) {
        val bottomNavigationView = view.bottomNavigationView
        bottomNavigationView.setOnNavigationItemSelectedListener(viewModel::onNavigationItemSelected)
    }
}