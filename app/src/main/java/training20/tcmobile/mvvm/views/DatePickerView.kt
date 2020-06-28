package training20.tcmobile.mvvm.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_hairdresser_salon_reservation_calendar_month.*
import kotlinx.android.synthetic.main.view_date_picker.view.*
import kotlinx.android.synthetic.main.view_hairdresser_salon_matching_calendar_table_cell.view.*
import kotlinx.android.synthetic.main.view_hairdresser_salon_matching_calendar_table_row.view.*
import training20.tcmobile.ApplicationContext
import training20.tcmobile.R
import java.lang.ref.WeakReference
import java.time.DayOfWeek
import java.time.LocalDate
import kotlin.random.Random

class DatePickerView: LinearLayout {

    interface OnCellClickListener {

        fun onCelClick(year: Int, month: Int, day: Int)
    }

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyle: Int) : super(context, attrs, defStyle) {
        LayoutInflater.from(context).inflate(R.layout.view_date_picker, this, true)
        setup()
    }

    private var onCellClickListener: WeakReference<OnCellClickListener>? = null

    fun setOnCellClickListener(onCellClickListener: OnCellClickListener) {
        this.onCellClickListener = WeakReference(onCellClickListener)
    }

    private fun setup() {
        var localDate = LocalDate.now().withDayOfMonth(1)
        while (localDate.dayOfWeek != DayOfWeek.SUNDAY) {
            localDate = localDate.minusDays(1)
        }
        val rows = arrayOf(row1, row2, row3, row4, row5, row6)
        rows.forEach { row ->
            val cells = arrayOf(row.cell1, row.cell2, row.cell3, row.cell4, row.cell5, row.cell6, row.cell7)
            cells.forEachIndexed { index, cell ->
                val dateText = cell.dateText
                val year = localDate.year
                val month = localDate.monthValue
                val day = localDate.dayOfMonth
                cell.setOnClickListener {
                    onCellClickListener?.get()?.onCelClick(year, month, day)
                    dateText.background = ContextCompat.getDrawable(ApplicationContext.context, R.drawable.date_picker_view_selected_text_view)
                    dateText.setTextColor(ContextCompat.getColor(ApplicationContext.context, R.color.colorOnPrimary))
                }
                dateText.text = localDate.dayOfMonth.toString()
                if (index == 0) {
                    dateText.setTextColor(ContextCompat.getColor(ApplicationContext.context, R.color.sunday))
                }
                if (index == 6) {
                    dateText.setTextColor(ContextCompat.getColor(ApplicationContext.context, R.color.saturday))
                }
                localDate = localDate.plusDays(1)
            }
        }
    }

}