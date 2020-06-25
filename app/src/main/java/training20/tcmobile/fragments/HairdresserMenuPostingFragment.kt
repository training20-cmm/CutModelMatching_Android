package training20.tcmobile.fragments

import android.app.Activity.RESULT_OK
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.view.marginBottom
import com.google.android.flexbox.*
import kotlinx.android.synthetic.main.fragment_hairdresser_menu_posting.*
import kotlinx.android.synthetic.main.fragment_hairdresser_menu_posting.view.*
import org.koin.android.ext.android.inject
import training20.tcmobile.databinding.FragmentHairdresserMenuPostingBinding
import training20.tcmobile.mvvm.actions.HairdresserMenuPostingActions
import training20.tcmobile.mvvm.viewmodels.DatePickerDialogFragment
import training20.tcmobile.mvvm.viewmodels.HairdresserMenuPostingViewModel
import training20.tcmobile.mvvm.viewmodels.TimePikerDialogFragment
import training20.tcmobile.ui.recyclerview.adapters.HairdresserMenuPostingRecyclerViewAdpter


//　viewを操作するところ
// イベント(onclickとか)はこっちに書いていく

class HairdresserMenuPostingFragment :
    BackableFragment<HairdresserMenuPostingActions, FragmentHairdresserMenuPostingBinding, HairdresserMenuPostingViewModel>(),
    HairdresserMenuPostingActions,
    DatePickerDialog.OnDateSetListener,
    TimePickerDialog.OnTimeSetListener
{
    override val viewModel: HairdresserMenuPostingViewModel by inject()

    private val adapter = HairdresserMenuPostingRecyclerViewAdpter(viewModel)
//    private lateinit var binding: FragmentHairdresserMenuPostingBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState) ?: return null
//        layoutSetup(view)
        selectPhoto(view)
        selectdate(view)
        selecttime(view)
        addtexts(view)
        return view
    }

    override fun createDataBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHairdresserMenuPostingBinding =
        FragmentHairdresserMenuPostingBinding.inflate(inflater, container, false)

    override fun setupViewModel(viewModel: HairdresserMenuPostingViewModel) {
        viewModel.eventDispatcher.bind(viewLifecycleOwner, this)
        viewModel.start()
    }

    override fun setupDataBinding(
        dataBinding: FragmentHairdresserMenuPostingBinding,
        savedInstanceState: Bundle?
    ) {
        dataBinding.viewModel = viewModel
    }


    //　ViewModelから情報を取得したお知らせをもらう
    // もらったら画面を再構成する
    override fun onTreatmentChanged() {
        adapter.notifyDataSetChanged()
    }

    // カレンダーで選択をするためのダイアログをだす処理
    private fun selectdate(view: View) {
        val datePicker = DatePickerDialogFragment.newInstance(this)
        view.selectDate.setOnClickListener {
            datePicker.show(childFragmentManager, "TAG")
        }
    }

    // 日付が選択されたあと呼び出されるところ
    // ここでview画面に表示させればいい感じ
    override fun onDateSet(view: DatePicker?, year: Int, month: Int, day: Int) {
        selectDate.setText(String.format("%d / %02d / %d", year, month+1, day))
        println("OK")
    }

    // 時計で時間を選択する処理
    private fun selecttime(view: View) {
        val timePicker = TimePikerDialogFragment.newInstance(this)
        view.startTime.setOnClickListener {
            timePicker.show(childFragmentManager, "timeTag")
        }
    }

    override fun onTimeSet(view: TimePicker?, hour: Int, minute: Int) {
        startTime.setText(String.format("%d : %d", hour, minute))
        println("time_ok")
    }

    // 希望日時を追加する
    private fun addtexts(view: View) {
        view.datetimeAdd.setOnClickListener {
            val day_time = TextView(activity)
            day_time.text = "希望日 : " + selectDate.text.toString() + "\n希望時間 : " + startTime.text.toString() + "\n所要時間 :" + requiredTime.text.toString() + "分"
            datetimeArea.addView(day_time)
            // マージン実装したいな……
        }
    }

    // flexboxLayoutのセットアップ
    private fun layoutSetup(view: View) {
        val flexboxLayoutManager = FlexboxLayoutManager(activity)
        flexboxLayoutManager.flexDirection = FlexDirection.COLUMN
        flexboxLayoutManager.flexWrap = FlexWrap.WRAP
        flexboxLayoutManager.justifyContent = JustifyContent.SPACE_AROUND
        flexboxLayoutManager.alignItems = AlignItems.STRETCH

        view.recyclerView.layoutManager = flexboxLayoutManager
        view.recyclerView.adapter = HairdresserMenuPostingRecyclerViewAdpter(viewModel)
    }

    // 写真を選択させたい
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

    //　選択した写真を表示させる
    override fun onActivityResult(requestCode: Int, resultCode: Int, resultData: Intent?) {
        super.onActivityResult(requestCode, resultCode, resultData)
        if (resultCode != RESULT_OK) {
            return
        }
        when (requestCode) {
            READ_REQUEST_CODE -> {
                try {
                    resultData?.data?.also { uri ->
                        Log.d("VIDEOURI", "URI:${uri}")
                        val inputStream = activity?.contentResolver?.openInputStream(uri)
                        val image = BitmapFactory.decodeStream(inputStream)
                        image_view.setImageBitmap(image)
                        // ボタンのテキスト変更する処理追加
                    }
                } catch (e: Exception) {
                    Log.d("ClickEvent", "show_photo_error")
                    Toast.makeText(activity, "画像を表示できません", Toast.LENGTH_LONG)
                }
            }
        }
    }
}