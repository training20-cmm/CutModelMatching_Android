package training20.tcmobile.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import com.google.android.material.navigation.NavigationView
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView
import io.realm.Realm
import kotlinx.android.synthetic.main.fragment_model_search_result.view.*
import kotlinx.android.synthetic.main.fragment_model_search_result.view.recyclerView
import kotlinx.android.synthetic.main.fragment_model_search_result.view.toolbar
import kotlinx.android.synthetic.main.view_model_search_result_menu_card.view.*
import kotlinx.android.synthetic.main.view_model_search_result_menu_card.view.menuTitleTextView
import kotlinx.android.synthetic.main.view_toolbar.view.*
import org.koin.android.ext.android.inject
import training20.tcmobile.R
import training20.tcmobile.SearchCondition
import training20.tcmobile.Tag
import training20.tcmobile.databinding.FragmentModelSearchResultBinding
import training20.tcmobile.mvvm.actions.ModelSearchResultActions
import training20.tcmobile.mvvm.viewmodels.ModelSearchResultViewModel
import training20.tcmobile.mvvm.views.TagsView
import training20.tcmobile.net.http.HttpClient
import training20.tcmobile.util.applyNotNull
import training20.tcmobile.util.ensureNotNull

class ModelSearchResultFragment:
    NavigationDrawerFragment<FragmentModelSearchResultBinding, ModelSearchResultViewModel>(),
    ModelSearchResultActions,
    NavigationView.OnNavigationItemSelectedListener
{

    private class RecyclerViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val cardView: MaterialCardView = itemView.cardView
        val menuImageView: ImageView = itemView.menuImageView
        val menuTitleTextView: TextView = itemView.menuTitleTextView
        val locationTextView: TextView = itemView.locationTextView
        val storeTextView: TextView = itemView.storeTextView
        val timeTextView: TextView = itemView.timeTextView
        val priceTextView: TextView = itemView.priceTextView
        val treatmentTagsView: TagsView = itemView.treatmentTagsView
        val menuDetailsTextView: TextView = itemView.menuDetailsTextView
        val menuTagsView: TagsView = itemView.menuTagsView
        val profileCircleImageView: CircleImageView = itemView.profileCircleImageView
        val userNameTextView: TextView = itemView.userNameTextView
        val ratingBar: RatingBar = itemView.ratingBar
        val ratingTextView: TextView = itemView.ratingTextView
        val experienceTextView: TextView = itemView.experienceTextView
        val ageTextView: TextView = itemView.ageTextView
        val genderTextView: TextView = itemView.genderTextView
    }

    private inner class RecyclerViewAdapter(
        private val viewModel: ModelSearchResultViewModel
    ): RecyclerView.Adapter<RecyclerViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.view_model_search_result_menu_card, parent, false)

            return RecyclerViewHolder(view)
        }

        override fun getItemCount(): Int {
            return viewModel.menus?.size ?: 0
        }

        override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
            val menu = viewModel.menus?.get(position)
            menu?.let {
                holder.cardView.setOnClickListener {
                    ensureNotNull(menu.id) { menuId ->
                        showMenu(menuId)
                    }
                }
                menu.images?.let { images ->
                    if (images.isEmpty()) {
                        return
                    }
                    Picasso.get().load(HttpClient.serverOrigin + images[0].path).into(holder.menuImageView)
                }
                holder.menuTitleTextView.text = menu.title
                holder.locationTextView.text = menu.hairdresser?.salon?.prefecture ?: ""
                holder.storeTextView.text = menu.hairdresser?.salon?.name ?: ""
                holder.timeTextView.text = "${menu.minutes.toString()}${getString(R.string.unit_minute)}"
                holder.priceTextView.text = "${getString(R.string.unit_currency_sign)}${menu.price.toString()}"
                menu.treatment?.let { treatmentList ->
                    val treatmentTags = treatmentList.mapNotNull { treatment ->
                        applyNotNull(treatment.name) { name ->
                            Tag(name, ContextCompat.getColor(activity!!, R.color.textColorSecondary), "#f0f0f0")
                        }
                    }.toTypedArray()
                    holder.treatmentTagsView.set(treatmentTags)
                }
                holder.menuDetailsTextView.text = menu.details.toString()
                menu.tags?.let { tags ->
                    val tagTags = tags.mapNotNull { tag ->
                        applyNotNull(tag.name, tag.color) { name, color ->
                            Tag(name, "#ffffff", "#${color}")
                        }
                    }.toTypedArray()
                    holder.menuTagsView.set(tagTags)
                }
                menu.hairdresser?.let { hairdresser ->
                    Picasso.get().load(HttpClient.serverOrigin + hairdresser.profileImagePath).into(holder.profileCircleImageView)
                    holder.userNameTextView.text = hairdresser.name
                    holder.ageTextView.text = "${hairdresser.age}${getString(R.string.age)}"
                    holder.ratingBar.rating = hairdresser.comprehensiveEvaluation ?: 0.0f
                    holder.ratingTextView.text = hairdresser.comprehensiveEvaluation.toString()
                    holder.experienceTextView.text = "${hairdresser.position?.name}${getString(R.string.history)}${hairdresser.years}${getString(R.string.year)}"
                    holder.genderTextView.text = "${hairdresser.gender}${getString(R.string.gender)}"
                }
            }
        }

    }

    override val viewModel: ModelSearchResultViewModel by inject()

    private val recyclerViewAdapter = RecyclerViewAdapter(viewModel)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState) ?: return null
        setupNavigationDrawer(view.toolbar as Toolbar, view.drawerLayout, view.navigationView, this)
        setupToolbar(view)
        setupRecyclerView(view)
        setupFab(view)
        return view
    }

    override fun createDataBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentModelSearchResultBinding = FragmentModelSearchResultBinding.inflate(inflater, container, false)

    override fun setupViewModel(viewModel: ModelSearchResultViewModel) {
        Realm.getDefaultInstance().executeTransaction {
            Realm.getDefaultInstance().copyToRealm(SearchCondition(prefecture = "大分県"))
        }
        val searchCondition = Realm.getDefaultInstance().where(SearchCondition::class.java).findFirst()
        searchCondition?.let {
            viewModel.eventDispatcher.bind(viewLifecycleOwner, this)
            viewModel.start(it)
        }
    }

    override fun setupDataBinding(
        dataBinding: FragmentModelSearchResultBinding,
        savedInstanceState: Bundle?
    ) {
        dataBinding.viewModel = viewModel
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        return true
    }

    override fun onMenuSearchSuccess() {
        view?.loadingSpinner?.visibility = View.GONE
        view?.recyclerView?.visibility = View.VISIBLE
        recyclerViewAdapter.notifyDataSetChanged()
    }

    override fun showMenu(menuId: Int) {
        val action = ModelSearchResultFragmentDirections.actionModelSearchResultFragmentToModelMenuFragment(
            menuId
        )
        findNavController().navigate(action)
    }

    override fun showMenuSearch() {
        findNavController().navigate(R.id.action_modelSearchResultFragment_to_modelMenuSearchFragment)
    }

    private fun setupToolbar(view: View) {
        view.toolbarBackButton.visibility = View.GONE
        view.toolbarTitleTextView.text = getString(R.string.fragment_model_menu_search_result_toolbar_title)
    }

    private fun setupRecyclerView(view: View) {
        view.recyclerView.adapter = recyclerViewAdapter
        view.recyclerView.layoutManager = LinearLayoutManager(activity)
    }

    private fun setupFab(view: View) {
        view.fab.setOnClickListener { showMenuSearch() }
    }
}