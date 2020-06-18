package training20.tcmobile.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.android.material.internal.NavigationMenu
import io.github.yavski.fabspeeddial.FabSpeedDial
import kotlinx.android.synthetic.main.fragment_hairdresser_home.*
import kotlinx.android.synthetic.main.fragment_hairdresser_home.view.*
import kotlinx.android.synthetic.main.fragment_hairdresser_home.view.drawerLayout
import kotlinx.android.synthetic.main.fragment_hairdresser_home.view.fabSpeedDial
import org.koin.android.ext.android.inject
import training20.tcmobile.R
import training20.tcmobile.databinding.FragmentHairdresserHomeBinding
import training20.tcmobile.mvvm.actions.HairdresserHomeActions
import training20.tcmobile.mvvm.event.EventDispatcher
import training20.tcmobile.mvvm.viewmodels.HairdresserHomeViewModel
import training20.tcmobile.ui.viewpager.adapter.HairdresserHomeViewPagerAdapter

class HairdresserHomeFragment :
    NavigationDrawerFragment<FragmentHairdresserHomeBinding, HairdresserHomeViewModel>(),
    HairdresserHomeActions
{

    private inner class FabSpeedDialMenuListener: FabSpeedDial.MenuListener {

        override fun onPrepareMenu(p0: NavigationMenu?): Boolean {
            return true
        }

        override fun onMenuItemSelected(menuItem: MenuItem?): Boolean {
            showHairstylePosting()
            return true
        }

        override fun onMenuClosed() {
        }
    }

    override val viewModel: HairdresserHomeViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState) ?: return null
        view.viewPager.adapter =
            HairdresserHomeViewPagerAdapter(
                childFragmentManager
            )
        view.tabLayout.setupWithViewPager(view.viewPager)
        setupNavigationDrawer(view.toolbar, view.drawerLayout)
        setupFabSpeedDial(view)
        return view
    }

    override fun createDataBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHairdresserHomeBinding = FragmentHairdresserHomeBinding.inflate(inflater, container, false)

    override fun setupViewModel(viewModel: HairdresserHomeViewModel) {
        viewModel.eventDispatcher.bind(viewLifecycleOwner, this)
    }

    override fun setupDataBinding(
        dataBinding: FragmentHairdresserHomeBinding,
        savedInstanceState: Bundle?
    ) {
        dataBinding.viewModel = viewModel
    }

    override fun showHairstylePosting() {
        findNavController().navigate(R.id.action_hairdresserFoundationFragment_to_hairdresserHairstylePostingFragment)
    }

    private fun setupFabSpeedDial(view: View) {
        view.fabSpeedDial.setMenuListener(FabSpeedDialMenuListener())
    }

//    private fun startFeatureDiscovery() {
//        val tabLayoutViewGroup = tabLayout.getChildAt(0) as ViewGroup
//        val tabItemHairCatalog = tabLayoutViewGroup.getChildAt(0)
//        val tabItemBlog = tabLayoutViewGroup.getChildAt(1)
//        val sequence = TapTargetSequence(this)
//        sequence.targets(
//            TapTarget.forToolbarNavigationIcon(toolbar,
//                getString(R.string.activity_hairdresser_home_feature_discovery_title_toolbar_navigation_button),
//                getString(R.string.activity_hairdresser_home_feature_discovery_description_toolbar_navigation_button)
//            )
//                .cancelable(false)
//                .tintTarget(false)
//                .id(1),
//            TapTarget.forView(
//                editProfileButton,
//                getString(R.string.activity_hairdresser_home_feature_discovery_title_edit_profile_button),
//                getString(R.string.activity_hairdresser_home_feature_discovery_description_edit_profile_button)
//            )
//                .cancelable(false)
//                .tintTarget(false)
//                .id(2),
//            TapTarget.forView(
//                tabItemHairCatalog,
//                getString(R.string.activity_hairdresser_home_feature_discovery_title_tab_item_hair_catalog),
//                getString(R.string.activity_hairdresser_home_feature_discovery_description_tab_item_hair_catalog)
//            )
//                .cancelable(false)
//                .id(3),
//            TapTarget.forView(
//                tabItemBlog,
//                getString(R.string.activity_hairdresser_home_feature_discovery_title_tab_item_blog),
//                getString(R.string.activity_hairdresser_home_feature_discovery_description_tab_item_blog)
//            )
//                .cancelable(false)
//                .id(4),
//            TapTarget.forView(
//                fabSpeedDial,
//                getString(R.string.activity_hairdresser_home_feature_discovery_title_fab_speed_dialog),
//                getString(R.string.activity_hairdresser_home_feature_discovery_description_fab_speed_dialog)
//            )
//                .tintTarget(false)
//                .id(5)
//        ).start()
//    }
//

}
