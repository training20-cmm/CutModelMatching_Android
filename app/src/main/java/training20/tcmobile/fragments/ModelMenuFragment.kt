package training20.tcmobile.fragments

import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.graphics.drawable.StateListDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.flexbox.*
import kotlinx.android.synthetic.main.view_toolbar.view.*
import kotlinx.android.synthetic.main.fragment_model_menu.view.*
import org.koin.android.ext.android.inject
import com.squareup.picasso.Picasso
import training20.tcmobile.ApplicationContext
import training20.tcmobile.R
import training20.tcmobile.databinding.FragmentModelMenuBinding
import training20.tcmobile.mvvm.actions.ModelMenuActions
import training20.tcmobile.mvvm.viewmodels.ModelMenuViewModel
import training20.tcmobile.net.http.HttpClient
import kotlin.text.Typography.times

class ModelMenuFragment :
    BackableFragment<ModelMenuActions, FragmentModelMenuBinding, ModelMenuViewModel>(),
    ModelMenuActions
{
    override val viewModel: ModelMenuViewModel by inject()
    private val items = ArrayList<FlexboxListItem>()
    private val adapter = FlexboxListAdapter()
    private val args: ModelMenuFragmentArgs by navArgs()
    var selectedMenuTimeId: Int? = null

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  super.onCreateView(inflater, container, savedInstanceState)?: return null

        view?.toolbarTitleTextView.text = "募集詳細"
        val toolbarRightButton = view.toolbarRightButton
        toolbarRightButton.visibility = View.VISIBLE
        toolbarRightButton.text = "予約"
        toolbarRightButton.setBackgroundColor(ContextCompat.getColor(ApplicationContext.context, R.color.colorAccent))
        toolbarRightButton.setOnClickListener {

            val action = ModelMenuFragmentDirections.actionModelMenuFragmentToModelReservationConfirmationFragment(2,selectedMenuTimeId!!)

            findNavController().navigate(action)
        }
        return view
    }

    override fun createDataBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentModelMenuBinding = FragmentModelMenuBinding.inflate(inflater, container, false)

    override fun setupViewModel(viewModel: ModelMenuViewModel) {
        viewModel.eventDispatcher.bind(viewLifecycleOwner, this)
        viewModel.start(args.menuId)
    }

    override fun setupDataBinding(
        dataBinding: FragmentModelMenuBinding,
        savedInstanceState: Bundle?
    ) {
        dataBinding.viewModel = viewModel
    }

    override fun onMenuCompleted() {

        setProfileImage()
        setTimes()
        setTags()
        setMenuImage()
        setTreatment()
        view?.priceValue?.text = viewModel.menu.value?.price.toString()


    }

    fun setProfileImage(){
        if(viewModel.menu.value?.hairdresser?.profileImagePath != null) {
            val imagepath = HttpClient.serverOrigin + viewModel.menu.value?.hairdresser?.profileImagePath

            Picasso.get()
                .load(imagepath)
                .into(view?.profileCircleImageView)
        }
    }

    fun setTags(){

        val flexboxLayoutManager = FlexboxLayoutManager(context)
        flexboxLayoutManager.flexDirection = FlexDirection.ROW
        // 折り返し方法を指定
        flexboxLayoutManager.flexWrap = FlexWrap.WRAP
        // 主軸方向の揃え位置を指定
        flexboxLayoutManager.justifyContent = JustifyContent.FLEX_START
        // 交差軸方向の揃え位置を指定
        flexboxLayoutManager.alignItems = AlignItems.STRETCH

        // RecyclerViewのLayoutManagerに、カスタムしたFlexboxLayoutManagerを指定
        view?.tags?.layoutManager = flexboxLayoutManager
        view?.tags?.adapter = adapter
        setupLocalItems()

    }

    private fun setupLocalItems(){

        for(value in viewModel.menu.value?.tags!!){
            value.name?.let { FlexboxListItem(it) }?.let { items.add(it) }
        }
        adapter.setItems(items)
    }

    fun setMenuImage() {
        val imageRoot = view?.imageLinear

        for ((index, value) in viewModel.menu.value?.images!!.withIndex()) {
            val imagepath = HttpClient.serverOrigin + value.path

            val imageView =
                activity?.layoutInflater?.inflate(R.layout.fragment_hairdresser_image_view, null) as ImageView

            val layoutParams = LinearLayout.LayoutParams(500, 500)
            imageView!!.layoutParams = layoutParams

            Picasso.get()
                .load(imagepath)
                .resize(300, 300)
                .into(imageView)

            imageRoot?.addView(imageView)
        }
    }

    fun setTimes(){
        var once = false
        val radioRoot = view?.radioGroup
        var first:RadioButton? = null


        for(value in viewModel?.menu.value?.time!!){
            if(!once){
                selectedMenuTimeId = value.id
                first = activity?.layoutInflater?.inflate(R.layout.view_radiobutton, null) as RadioButton
                first.text = value.date + "：" + value.start.toString() + ":00～"
                first.setOnClickListener {
                    selectedMenuTimeId = value.id
                }


                radioRoot?.addView(first)
                once = true
            }
            else{
                val radioButton = activity?.layoutInflater?.inflate(R.layout.view_radiobutton, null) as RadioButton
                radioButton.text = value.date + "：" +value.start.toString() + ":00～"
                radioButton.setOnClickListener {
                    selectedMenuTimeId = value.id
                }

                radioRoot?.addView(radioButton)
            }
        }
        first?.isChecked = true
    }

    fun setTreatment(){
        val treatmentRoot = view?.treatments

        for((index,value) in viewModel.menu.value?.treatment?.withIndex()!!){
            val textView =
                activity?.layoutInflater?.inflate(R.layout.fragment_model_text_view, null) as TextView

            textView.text = value.name

            treatmentRoot?.addView(textView)
        }
    }



}