package training20.tcmobile.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_hairdresser_salon_unregistered.view.*
import org.koin.android.ext.android.inject
import training20.tcmobile.R
import training20.tcmobile.activities.HairdresserMainActivity
import training20.tcmobile.databinding.FragmentHairdresserSalonUnregisteredBinding
import training20.tcmobile.mvvm.actions.HairdresserSalonRegistrationActions
import training20.tcmobile.mvvm.actions.HairdresserSalonUnregisteredActions
import training20.tcmobile.mvvm.viewmodels.HairdresserSalonUnregisteredViewModel

class HairdresserSalonUnregisteredFragment :
    BackableFragment<HairdresserSalonUnregisteredActions, FragmentHairdresserSalonUnregisteredBinding, HairdresserSalonUnregisteredViewModel>(),
    HairdresserSalonUnregisteredActions
{

    override val viewModel: HairdresserSalonUnregisteredViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //xmlのレイアウトをここでViewとして作成する
        val view = inflater.inflate(R.layout.fragment_hairdresser_salon_unregistered, container, false)

        //はいを押してサロン登録画面へ遷移する
        view.hairdresserSalonUnregistered_yes.setOnClickListener {v ->
            findNavController().navigate(R.id.action_hairdresserSalonUnregisteredFragment_to_hairdresserSalonRegistrationFragment)
        }
        //いいえを押してホームへ遷移する
        view.hairdresserSalonUnregistered_no.setOnClickListener {v ->
            findNavController().navigate(R.id.hairdresserFoundationFragment)
        }
        //戻るボタンを押して遷移する
        view.backButton.setOnClickListener {v ->
            back()
        }

        return inflater.inflate(R.layout.fragment_hairdresser_salon_unregistered, container, false)
    }

    override fun createDataBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHairdresserSalonUnregisteredBinding = FragmentHairdresserSalonUnregisteredBinding.inflate(inflater, container, false)

    override fun setupViewModel(viewModel: HairdresserSalonUnregisteredViewModel) {
        viewModel.eventDispatcher.bind(viewLifecycleOwner, this)
    }

    override fun setupDataBinding(
        dataBinding: FragmentHairdresserSalonUnregisteredBinding,
        savedInstanceState: Bundle?
    ) {
        dataBinding.viewModel = viewModel
    }

}



