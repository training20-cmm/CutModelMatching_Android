package training20.tcmobile.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TableRow
import kotlinx.android.synthetic.main.hairdresser_activity_salon_matching_calendar.*
import kotlinx.android.synthetic.main.hairdresser_fragment_salon_matching_calendar_table_cell.*
import kotlinx.android.synthetic.main.hairdresser_fragment_salon_matching_calendar_table_cell.view.*
import kotlinx.android.synthetic.main.hairdresser_fragment_salon_matching_calendar_table_row.*
import kotlinx.android.synthetic.main.hairdresser_fragment_salon_matching_calendar_table_row.view.*
import kotlinx.android.synthetic.main.hairdresser_fragment_salon_matching_calendar_cell_label.view.*
import training20.tcmobile.R
import java.time.DayOfWeek
import java.time.LocalDate
import kotlin.random.Random

class HairdresserSalonMatchingCalendarActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.hairdresser_activity_salon_matching_calendar)
        setupViews()
    }

    private fun setupViews() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)
        setupCalendarTableLayout()
    }

    private fun setupCalendarTableLayout() {
        var localDate = LocalDate.now().withDayOfMonth(1)
        while (localDate.dayOfWeek != DayOfWeek.SUNDAY) {
            localDate = localDate.minusDays(1)
        }
        val rows = arrayOf(row1, row2, row3, row4, row5, row6)
        rows.forEach { row ->

            val cells = arrayOf(row.cell1, row.cell2, row.cell3, row.cell4, row.cell5, row.cell6, row.cell7)
            cells.forEach { cell ->
                cell.dateText.text = localDate.dayOfMonth.toString()
                localDate = localDate.plusDays(1)
                if (Random.nextInt(30) % 4 == 0) {
                    val numberOfRecruitment = Random.nextInt(48)
                    val numberOfApplicants = (numberOfRecruitment * Random.nextFloat()).toInt()
                    cell.applicantsTextView.text = "${numberOfApplicants}/${numberOfRecruitment}"
                    cell.applicantsProgressBar.max = numberOfRecruitment
                    cell.applicantsProgressBar.progress = numberOfApplicants
                }
            }
        }
    }
}
