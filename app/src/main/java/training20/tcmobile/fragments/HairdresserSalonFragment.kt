package training20.tcmobile.fragments

import android.graphics.Color
import android.location.Geocoder
import android.os.AsyncTask
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.graphics.toColor
import androidx.navigation.fragment.findNavController
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_hairdresser_salon_reservation_calendar_month.*
import kotlinx.android.synthetic.main.fragment_hairdresser_salon.*
import kotlinx.android.synthetic.main.fragment_hairdresser_salon.view.*
import kotlinx.android.synthetic.main.view_hairdresser_salon_matching_calendar_table_cell.view.*
import kotlinx.android.synthetic.main.view_hairdresser_salon_matching_calendar_table_row.view.*
import kotlinx.android.synthetic.main.view_toolbar.view.*
import org.koin.android.ext.android.inject
import training20.tcmobile.R
import training20.tcmobile.databinding.FragmentHairdresserSalonBinding
import training20.tcmobile.mvvm.actions.HairdresserSalonActions
import training20.tcmobile.mvvm.viewmodels.HairdresserSalonViewModel
import training20.tcmobile.net.http.HttpClient
import java.time.DayOfWeek
import java.time.LocalDate
import java.util.*
import kotlin.concurrent.thread
import kotlin.random.Random


class HairdresserSalonFragment :
    BackableFragment<HairdresserSalonActions, FragmentHairdresserSalonBinding, HairdresserSalonViewModel>(),
    HairdresserSalonActions,OnMapReadyCallback
{
    private lateinit var mapView: MapView
    private lateinit var gMap: GoogleMap

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mapView.onSaveInstanceState(outState)
    }

    override val viewModel: HairdresserSalonViewModel by inject()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_hairdresser_salon, container, false)
        val view =  super.onCreateView(inflater, container, savedInstanceState)?: return null

        if (view != null) {
            mapView = view.mapView
        }
        mapView?.onCreate(savedInstanceState)
        mapView?.getMapAsync(this)

        view.toolbarBackButton.setOnClickListener {
            findNavController().navigate(R.id.action_hairdresserSalonFragment_to_hairdresserHomeFragment)
        }
        view.toolbarTitleTextView.text = getString(R.string.fragment_hairdresser_salon_toolbar_title)

        return view
    }

    override fun onMapReady(gm: GoogleMap?) {
        gMap = gm!!
        //googleMap?.getUiSettings()?.setZoomControlsEnabled(true)
        //googleMap?.
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView.onDestroy()
    }


    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }



    override fun createDataBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHairdresserSalonBinding = FragmentHairdresserSalonBinding.inflate(inflater, container, false)


    override fun setupViewModel(viewModel: HairdresserSalonViewModel) {
        viewModel.eventDispatcher.bind(viewLifecycleOwner, this)

        viewModel.start()
    }

    override fun setupDataBinding(
        dataBinding: FragmentHairdresserSalonBinding,
        savedInstanceState: Bundle?
    ) {
        dataBinding.viewModel = viewModel
    }

    override fun onSalonCompleted() {

        view?.loadingSpinner?.visibility = View.GONE
        view?.scrollView?.visibility = View.VISIBLE

        val imageRoot = view?.imageLinearLayout

        for((index,value) in viewModel.salon.value?.images!!.withIndex()) {

            val imagepath = HttpClient.serverOrigin + viewModel.salon.value?.images?.get(index)?.path

            val imageView = activity?.layoutInflater?.inflate(R.layout.fragment_hairdreser_image_view, null) as ImageView?

            val layoutParams = LinearLayout.LayoutParams(500,500)
            imageView!!.layoutParams = layoutParams

            Picasso.get()
                .load(imagepath)
                .resize(300,300)
                .into(imageView)

            imageRoot?.addView(imageView)
        }

        //席数
        val capacityText = view?.salonCapacityValue
        val capacity : String = viewModel.salon.value?.capacity.toString()
        capacityText?.text = capacity

        //
        val paymentText = view?.salonPaymentValue
        for (value in viewModel.salon.value?.paymentMethods!!) {
            paymentText?.text = value.name
        }

        val weekdayText = view?.weekdayValue
        val weekendText = view?.weekendValue
        weekdayText?.text = "平日：" + viewModel.salon.value?.openHoursWeekdays + "～" + viewModel.salon.value?.closeHoursWeekdays
        weekendText?.text = "休日：" + viewModel.salon.value?.openHoursWeekends + "～" + viewModel.salon.value?.closeHoursWeekends

        val regularHoliday = view?.regularHoliday
        regularHoliday?.text = "定休日：" + viewModel.salon.value?.regularHoliday + "曜"


        setupCalendarTableLayout()

        //addMarkerSalonLocation()

    }

    fun addMarkerSalonLocation(){
        val gcoder = Geocoder(context,Locale.getDefault())
        val locationInfo = gcoder.getFromLocationName(viewModel.salon.value?.address, 1)

        val latitude = locationInfo[0].latitude
        val longitude = locationInfo[0].longitude

        gMap.getUiSettings()?.setZoomControlsEnabled(true)
        gMap.addMarker(MarkerOptions().position(LatLng(latitude, longitude)))
        gMap.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(latitude,longitude), 16F))
    }


    fun setupCalendarTableLayout() {
        val week = arrayOf("日","月","火","水","木","金","土")
        var regularHoliday: Int? = null
        for ((index,value) in week.withIndex()){
            if(value == viewModel.salon.value?.regularHoliday){
                regularHoliday = index
            }
        }


        var localDate = LocalDate.now().withDayOfMonth(1)
        while (localDate.dayOfWeek != DayOfWeek.SUNDAY) {
            localDate = localDate.minusDays(1)
        }
        val rows = arrayOf(view?.row1, view?.row2, view?.row3, view?.row4, view?.row5, view?.row6)
        rows.forEach { row ->
            val cells = arrayOf(row?.cell1, row?.cell2, row?.cell3, row?.cell4, row?.cell5, row?.cell6, row?.cell7)
            for((index,cell) in cells.withIndex()){
                cell?.dateText?.text = localDate.dayOfMonth.toString()
                localDate = localDate.plusDays(1)
                if(index == regularHoliday){
                    cell?.dateText?.setTextColor(Color.RED)
                }
            }
        }
    }

}
