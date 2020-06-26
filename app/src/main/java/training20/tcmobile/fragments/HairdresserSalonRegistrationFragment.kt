package training20.tcmobile.fragments

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_hairdresser_salon_registration.*
import kotlinx.android.synthetic.main.fragment_hairdresser_salon_registration.view.*
import org.koin.android.ext.android.inject
import training20.tcmobile.R
import training20.tcmobile.databinding.FragmentHairdresserSalonRegistrationBinding
import training20.tcmobile.mvvm.actions.HairdresserSalonRegistrationActions
import training20.tcmobile.mvvm.viewmodels.HairdresserSalonRegistrationViewModel

class HairdresserSalonRegistrationFragment:
    BackableFragment<HairdresserSalonRegistrationActions, FragmentHairdresserSalonRegistrationBinding, HairdresserSalonRegistrationViewModel>(),
    HairdresserSalonRegistrationActions
{

    override val viewModel: HairdresserSalonRegistrationViewModel by inject()

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setupRegistrationButton()
//    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState) ?: return null
        setupRegistrationButton(view)
        selectPhoto(view)
        return view
    }

    override fun createDataBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHairdresserSalonRegistrationBinding = FragmentHairdresserSalonRegistrationBinding.inflate(inflater, container, false)

    override fun setupViewModel(viewModel: HairdresserSalonRegistrationViewModel) {
        viewModel.eventDispatcher.bind(viewLifecycleOwner, this)
        viewModel.prefectures = resources.getStringArray(R.array.prefectures)
        viewModel.week = resources.getStringArray(R.array.week)
    }

    override fun setupDataBinding(
        dataBinding: FragmentHairdresserSalonRegistrationBinding,
        savedInstanceState: Bundle?
    ) {
        dataBinding.viewModel = viewModel
    }

    private fun selectPhoto(view: View) {
        view.photoBtn.setOnClickListener {
            val intent = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
                addCategory(Intent.CATEGORY_OPENABLE)
                type = "image/*"
            }
            startActivityForResult(intent, READ_REQUEST_CODE);
        }
    }

    companion object {
        private const val READ_REQUEST_CODE: Int = 42
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, resultData: Intent?) {
        super.onActivityResult(requestCode, resultCode, resultData)
        if (resultCode != RESULT_OK) {
            return
        }
        when (requestCode) {
            READ_REQUEST_CODE -> {
                try {
                    resultData?.data?.also { uri ->
                        Log.d("VIDEOURI", "URI${uri}")
                        val inputStream = activity?.contentResolver?.openInputStream(uri)
                        val image = BitmapFactory.decodeStream(inputStream)
                        image_view.setImageBitmap(image)
                    }
                } catch (e: Exception) {
                    Log.d("ClickEvent", "show_photo_error")
                    Toast.makeText(activity, "エラーが発生しました", Toast.LENGTH_LONG)
                }
            }
        }
    }

    private fun setupRegistrationButton(view: View) {
        view.registrationButton.setOnClickListener(this::onRegistrationButtonClicked)
    }

    private fun onRegistrationButtonClicked(v: View) {
        view?.registrationButton?.visibility = View.GONE
        view?.registrationSpinner?.visibility = View.VISIBLE
        viewModel.registerHairdresser()
    }
}