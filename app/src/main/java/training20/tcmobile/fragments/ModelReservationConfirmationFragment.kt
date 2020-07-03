package training20.tcmobile.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import kotlinx.android.synthetic.main.fragment_model_menu.view.*
import kotlinx.android.synthetic.main.view_toolbar.view.*
import kotlinx.android.synthetic.main.fragment_model_reservation_confirmation.*
import kotlinx.android.synthetic.main.fragment_model_reservation_confirmation.view.*
import kotlinx.android.synthetic.main.fragment_model_reservation_confirmation.view.treatments
import org.koin.android.ext.android.inject
import training20.tcmobile.R
import training20.tcmobile.databinding.FragmentModelReservationConfirmationBinding
import training20.tcmobile.mvvm.MvvmFragment
import training20.tcmobile.mvvm.actions.ModelReservationConfirmationActions
import training20.tcmobile.mvvm.viewmodels.ModelReservationConfirmationViewModel

class ModelReservationConfirmationFragment :
    BackableFragment<ModelReservationConfirmationActions, FragmentModelReservationConfirmationBinding, ModelReservationConfirmationViewModel>(),
    ModelReservationConfirmationActions
{

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view = super.onCreateView(inflater, container, savedInstanceState)?: return null

        view?.toolbarTitleTextView.text = "予約確認"


        view.toolbarBackButton.setOnClickListener {
            findNavController().navigateUp()
        }

        view?.reservationButton.setOnClickListener{
            if(view?.ConfirmationcheckBox.isChecked())
            viewModel.reservation(args.menuId,args.menuTimeId)
            //findNavController().navigate()
        }
        return view
    }

    override val viewModel: ModelReservationConfirmationViewModel by inject()
    private val args: ModelReservationConfirmationFragmentArgs by navArgs()


    override fun createDataBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentModelReservationConfirmationBinding = FragmentModelReservationConfirmationBinding.inflate(inflater, container, false)

    override fun setupViewModel(viewModel: ModelReservationConfirmationViewModel) {
        viewModel.eventDispatcher.bind(viewLifecycleOwner, this)
        viewModel.start(args.menuId,args.menuTimeId)
    }

    override fun setupDataBinding(
        dataBinding: FragmentModelReservationConfirmationBinding,
        savedInstanceState: Bundle?
    ) {
        dataBinding.viewModel = viewModel
    }

    override fun onMenuCompleted() {
        setTreatment()

        view?.priceValue2?.text = viewModel.menu.value?.price.toString()

        //view?.dateValue?.text = viewModel.menu?.value?.time.start.toString()

        for(value in viewModel.menu.value?.time!!){
            if(args.menuTimeId == value.id){
                view?.dateValue?.text = value.date
                view?.timeValue?.text = value.start.toString() + ":00"
            }
        }
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