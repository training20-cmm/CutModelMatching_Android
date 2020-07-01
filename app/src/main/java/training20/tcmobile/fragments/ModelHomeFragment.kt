package training20.tcmobile.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_model_home.*
import org.koin.android.ext.android.inject
import training20.tcmobile.R
import training20.tcmobile.databinding.FragmentModelHomeBinding
import training20.tcmobile.mvvm.MvvmFragment
import training20.tcmobile.mvvm.actions.ModelHomeActions
import training20.tcmobile.mvvm.viewmodels.ModelHomeViewModel
import training20.tcmobile.net.http.HttpClient
import training20.tcmobile.util.ensureNotNull

class ModelHomeFragment :
    MvvmFragment<FragmentModelHomeBinding, ModelHomeViewModel>(),
    ModelHomeActions
{

    override val viewModel: ModelHomeViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun createDataBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentModelHomeBinding = FragmentModelHomeBinding.inflate(inflater, container, false)

    override fun setupViewModel(viewModel: ModelHomeViewModel) {
        viewModel.eventDispatcher.bind(viewLifecycleOwner, this)
        viewModel.start()
    }

    override fun setupDataBinding(
        dataBinding: FragmentModelHomeBinding,
        savedInstanceState: Bundle?
    ) {
        dataBinding.viewModel = viewModel
    }

    override fun onNextReservationFetched() {
        ensureNotNull(viewModel.nextReservation.value?.menu?.hairdresser) { hairdresser ->
            ensureNotNull(hairdresser.profileImagePath, hairdresser.name) { profileImagePath, name ->
                Picasso.get().load(HttpClient.serverOrigin + profileImagePath).into(profileCircleImageView)
                hairdresserNameTextView.text = name
            }
            ensureNotNull(hairdresser.salon) { salon ->
                locationTextView.text = salon.prefecture
                storeTextView.text = salon.name
            }
        }
        ensureNotNull(viewModel.nextReservation.value?.menu?.time) { menuTimeList ->
            val menuTime = menuTimeList.get(0)
            dateTextView.text = "${menuTime.date} ${menuTime.start?.toString()}:00"
        }
        nextReservationCardView.visibility = View.VISIBLE
    }

    override fun onNextReservationNotFound() {
        notFoundNextReservationCardView.visibility = View.VISIBLE
    }


}