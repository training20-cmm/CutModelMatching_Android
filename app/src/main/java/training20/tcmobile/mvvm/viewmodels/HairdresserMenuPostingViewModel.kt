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

    val response:Array<response_sample> = arrayOf(response_sample(1, "test1"),
        response_sample(2, "test2"), response_sample(3, "test3"),
        response_sample(4, "test4"), response_sample(5, "test5"))

    var title = ""
    var price = ""
    var details = ""
    var requiredtime = ""
    var timeDates: Array<String> = arrayOf()
    var minutes = ""
    var gender = ""
    var male = false
    var female = false
    var resource = 0
    var hairdresser_id = 0

    fun onclickPosting() {
        Log.d("CheckClick", "click!")
        gender = if (male) "男" else "女"
        menuRepository.store(
            title,
            details,
            timeDates,
            gender,
            price,
            minutes,
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
