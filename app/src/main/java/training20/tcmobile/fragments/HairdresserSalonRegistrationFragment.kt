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
import androidx.databinding.ObservableInt
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_hairdresser_salon_registration.*
import kotlinx.android.synthetic.main.fragment_hairdresser_salon_registration.view.*
import kotlinx.android.synthetic.main.view_toolbar.view.*
import org.koin.android.ext.android.inject
import training20.tcmobile.R
import training20.tcmobile.databinding.FragmentHairdresserSalonRegistrationBinding
import training20.tcmobile.mvvm.actions.HairdresserSalonRegistrationActions
import training20.tcmobile.mvvm.viewmodels.HairdresserSalonRegistrationViewModel
import java.io.File

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
        setupToolbar(view)
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

    override fun showSalon() {
        findNavController().navigate(R.id.action_hairdresserSalonRegistrationFragment_to_hairdresserSalonFragment)
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

    private fun setupToolbar(view: View) {
        view.toolbarBackButton.setOnClickListener {
            viewModel.onBack()
        }
        view.toolbarTitleTextView.text = getString(R.string.fragment_hairdresser_salon_registration_toolbar_title)
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
                        println(uri.toString())
                        val file = File(uri.toString())
                        println(file)
                        viewModel.uri = uri.toString()
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
        view?.registrationProgressbar?.visibility = View.VISIBLE
        viewModel.registerHairdresser()
    }
}