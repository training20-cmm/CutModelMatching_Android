package training20.tcmobile.fragments

import android.app.Activity.RESULT_OK
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.view.marginBottom
import com.google.android.flexbox.*
import kotlinx.android.synthetic.main.fragment_hairdresser_menu_posting.*
import kotlinx.android.synthetic.main.fragment_hairdresser_menu_posting.view.*
import kotlinx.android.synthetic.main.fragment_tag_selection.*
import kotlinx.android.synthetic.main.fragment_tag_selection.view.*
import org.koin.android.ext.android.inject
import training20.tcmobile.Tag
import training20.tcmobile.auth.AuthManager
import training20.tcmobile.databinding.FragmentHairdresserMenuPostingBinding
import training20.tcmobile.mvvm.actions.HairdresserMenuPostingActions
import training20.tcmobile.mvvm.models.Hairdresser
import training20.tcmobile.mvvm.viewmodels.DatePickerDialogFragment
import training20.tcmobile.mvvm.viewmodels.HairdresserMenuPostingViewModel
import training20.tcmobile.mvvm.viewmodels.TimePikerDialogFragment
import training20.tcmobile.ui.recyclerview.adapters.HairdresserMenuPostingRecyclerViewAdpter
import training20.tcmobile.util.applyNotNull


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
    private val authManager: AuthManager by inject()
    private val hairdresser = authManager.currentHairdresser()
//    private lateinit var binding: FragmentHairdresserMenuPostingBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState) ?: return null
        layoutSetup(view)
        view.button.setOnClickListener {
            val hairdresser = authManager.currentHairdresser()
            viewModel.hairdresser_id = hairdresser?.id ?: 0
            viewModel.onclickPosting()
        }
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
        //viewModel.hairdresser_id = hairdresser?.id ?: 0
        val hairdresser = authManager.currentHairdresser()
        println(hairdresser)
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

    override fun onMenuTagCategoriesChanged() {
        setupTagSelectionFragment()
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
        selectDate.setText(String.format("%d/%02d/%02d", year, month+1, day))
        println("date_OK")
    }

    // 時計で時間を選択する処理
    private fun selecttime(view: View) {
        val timePicker = TimePikerDialogFragment.newInstance(this)
        view.startTime.setOnClickListener {
            timePicker.show(childFragmentManager, "timeTag")
        }
    }

    private fun setupTagSelectionFragment() {
        val tabs = viewModel.menuTagCategories?.mapNotNull { menuTagCategory ->
            val tags = menuTagCategory.tags?.mapNotNull {
                applyNotNull(it.id, it.name) { id, name ->
                    TagSelectionFragment.Tag(id, name)
                }
            }?.toMutableList()
            applyNotNull(menuTagCategory.name, tags) { name, tags ->
                TagSelectionFragment.Tab(name, tags)
            }
        }?.toMutableList() ?: return
        val tagListItemClickListener: (TagSelectionFragment.Tag) -> Unit = { tag ->
            //*******************************************************************
            // NOTE: このidをサーバに送る
            println(tag.id)
            //*******************************************************************
        }
        val adapter = TagSelectionFragment.Adapter(childFragmentManager, tabs, tagListItemClickListener)
        val tabPager = view?.tabPager
        tabPager?.adapter = adapter
        view?.tabLayout?.setupWithViewPager(tabPager)

        view?.filterInput?.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                adapter.filter(s.toString())
            }
            override fun afterTextChanged(s: Editable?) {
            }
        })
    }

    override fun onTimeSet(view: TimePicker?, hour: Int, minute: Int) {
        startTime.setText(String.format("%02d:%02d", hour, minute))
        println("time_ok")
    }

    // 希望日時を追加する
    private fun addtexts(view: View) {
        view.datetimeAdd.setOnClickListener {
            val date = selectDate.text.toString()
            val starttime = startTime.text.toString()
            // 日付を配列にいれたい
            viewModel.timeDates.add(date)
            viewModel.timeStarts.add(starttime)
            Log.d("click_add", "time&date_add!")
            println("ok")
            // 表示させたい
            val day_time = TextView(activity)
            day_time.text = "希望日 : " + date + "\n希望時間 : " + starttime
            datetimeArea.addView(day_time)
            // マージン実装したいな……
        }
    }

    // flexboxLayoutのセットアップ
    private fun layoutSetup(view: View) {
        val flexboxLayoutManager = FlexboxLayoutManager(activity)
        flexboxLayoutManager.flexDirection = FlexDirection.ROW
        flexboxLayoutManager.flexWrap = FlexWrap.WRAP

        view.recyclerView.layoutManager = flexboxLayoutManager
        view.recyclerView.adapter = HairdresserMenuPostingRecyclerViewAdpter(viewModel)
         //HairdresserMenuPostingRecyclerViewAdpter(viewModel).setdata(viewModel.response)
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