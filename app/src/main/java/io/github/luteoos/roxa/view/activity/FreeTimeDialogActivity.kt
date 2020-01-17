package io.github.luteoos.roxa.view.activity

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import es.dmoral.toasty.Toasty
import io.github.luteoos.roxa.R
import io.github.luteoos.roxa.baseAbstract.BaseActivity
import io.github.luteoos.roxa.utils.getSDFdate
import io.github.luteoos.roxa.viewmodel.DialogActivityViewModel
import kotlinx.android.synthetic.main.activity_dialog_free_time.*
import java.util.*

class FreeTimeDialogActivity : BaseActivity<DialogActivityViewModel>() {

    override fun getLayoutID(): Int = R.layout.activity_dialog_free_time

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setBindings()
    }

    private fun setBindings(){
        setTitle(R.string.create_free_time_title)
        btnOk.setOnClickListener {

        }
        btnCancel.setOnClickListener {
            finish()
        }
        btnPickStart.setOnClickListener {
            pickDateTime{ calendar ->
                tvStartDate.text = calendar.getSDFdate()
            }
        }
        btnPickEnd.setOnClickListener {
            pickDateTime { calendar ->
                tvEndDate.text = calendar.getSDFdate()
            }
        }

    }

    private fun pickDateTime(confirm: (Calendar) -> Unit) {
        val currentDateTime = Calendar.getInstance()
        val startYear = currentDateTime.get(Calendar.YEAR)
        val startMonth = currentDateTime.get(Calendar.MONTH)
        val startDay = currentDateTime.get(Calendar.DAY_OF_MONTH)
        val startHour = currentDateTime.get(Calendar.HOUR_OF_DAY)
        val startMinute = currentDateTime.get(Calendar.MINUTE)

        DatePickerDialog(this, DatePickerDialog.OnDateSetListener { _, year, month, day ->
            TimePickerDialog(this, TimePickerDialog.OnTimeSetListener { _, hour, minute ->
                val pickedDateTime = Calendar.getInstance()
                pickedDateTime.set(year, month, day, hour, minute)
                confirm(pickedDateTime)
            }, startHour, startMinute, false).show()
        }, startYear, startMonth, startDay).let {
            it.datePicker.minDate = currentDateTime.timeInMillis
            it.show()
        }
    }
}