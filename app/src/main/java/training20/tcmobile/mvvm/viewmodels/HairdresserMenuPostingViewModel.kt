package training20.tcmobile.mvvm.viewmodels


import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.app.Dialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.util.Log
import androidx.databinding.ObservableInt
import androidx.fragment.app.DialogFragment
import training20.tcmobile.mvvm.actions.HairdresserMenuPostingActions
import training20.tcmobile.mvvm.event.EventDispatcher
import training20.tcmobile.mvvm.repositories.MenuRepositoryHttp
import java.util.*


// viewを操作してはいけない


// タグをとってくるためのとりあえずのクラス
// その打ち消す
class response_sample(
    val id:Int,
    val name:String
)

// 施術内容を表示
// DBに内容を送信する処理

class HairdresserMenuPostingViewModel(
    eventDispatcher: EventDispatcher<HairdresserMenuPostingActions>
): BackableViewModel<HairdresserMenuPostingActions>(eventDispatcher)
{

    val menuRepository = MenuRepositoryHttp()

    val response:Array<response_sample> = arrayOf(response_sample(1, "カット"),
        response_sample(2, "パーマ"), response_sample(3, "縮毛矯正"),
        response_sample(4, "エクステ"), response_sample(5, "シャンプー"),
        response_sample(6, "トリートメント"), response_sample(7, "ヘッドスパ"),
        response_sample(8, "ヘアセット"))

    var title = "test"
    var price = "1000"
    var details = "sample_sample_sample"
    var requiredtime = "20"
    var timeDates: MutableList<String> = mutableListOf("2020/12/12", "2020/12/13")
    var timeStarts: MutableList<String> = mutableListOf("10:00", "11:00")
    var minutes = "20"
    var gender = ""
    var male = false
    var female = false
    var hairdresser_id = 0
    var imageURI = ""
    var treatment: MutableList<Int> = mutableListOf()
    var checked = false

    fun onclickPosting() {
        Log.d("CheckClick", "click!")
        gender = if (male) "男" else "女"
        menuRepository.store(
            title,
            details,
            timeDates,
            timeStarts,
            gender,
            price,
            minutes,
            treatment,
            hairdresser_id,
            onSuccess = this::onMenuStoreSuccess
        )
    }

    fun start() {
        //　リポジトリからデータ撮ってくる処理、が今回は初期値で持っているので省略。
       onHairdresserOrdersSuccess(response)
    }


    fun onHairdresserOrdersSuccess(response:Array<response_sample>) {
        // 処理省略
        eventDispatcher.dispatchEvent { onTreatmentChanged() }
    }

    private fun onMenuStoreSuccess() {
        println("OK")
    }

    fun itemcheck(id: Int) {
        Log.d("checkbox", "click")
        if (treatment.contains(id))  treatment.removeAt(treatment.indexOf(id)) else treatment.add(id)
    }
}

// カレンダー形式で日付を詮索させたい
class DatePickerDialogFragment : DialogFragment() {
    companion object {
        fun newInstance(onDateSetListener: OnDateSetListener): DatePickerDialogFragment {
            val datePickerDialogFragment = DatePickerDialogFragment()
            datePickerDialogFragment.onDateSetListener = onDateSetListener
            return datePickerDialogFragment
        }
    }

    private var onDateSetListener: OnDateSetListener? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val calendar: Calendar = Calendar.getInstance()
        val year: Int = calendar.get(Calendar.YEAR)
        val month: Int = calendar.get(Calendar.MONTH)
        val dayOfMonth: Int = calendar.get(Calendar.DAY_OF_MONTH)
        return DatePickerDialog(requireActivity(), onDateSetListener, year, month, dayOfMonth)
    }
}


//　時計式で時間選択
class TimePikerDialogFragment : DialogFragment() {
    companion object {
        fun newInstance(onTimeSetListener: TimePickerDialog.OnTimeSetListener): TimePikerDialogFragment {
            val timePikerDialogFragment = TimePikerDialogFragment()
            timePikerDialogFragment.onTimeSetListener = onTimeSetListener
            return timePikerDialogFragment
        }
    }

    private var onTimeSetListener: TimePickerDialog.OnTimeSetListener? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val c = Calendar.getInstance()
        val hour = c[Calendar.HOUR_OF_DAY]
        val minute = c[Calendar.MINUTE]
        return TimePickerDialog(requireActivity(), onTimeSetListener, hour, minute, true)
    }
}
