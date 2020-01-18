package io.github.luteoos.roxa.utils

import android.content.Context
import android.widget.ListView
import timber.log.Timber
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*


fun String.getFormattedDate(outputPattern: String = "yyyy-MM-dd HH:mm"): String {
    var formattedDateString = ""
    try {
        val sdf = SimpleDateFormat("MM-dd-yyyy HH:mm", Locale.getDefault())
        sdf.timeZone = TimeZone.getDefault()
        sdf.parse(this)?.let {
            formattedDateString = SimpleDateFormat(outputPattern, Locale.getDefault()).format(it)
        }
    } catch (e: Exception) {
        Timber.e(e)
    }
    return formattedDateString
}

fun String.toCalendarInstance(inputPattern: String = "MM-dd-yyyy HH:mm"): Calendar{
    val calendar = Calendar.getInstance()
    calendar.time = SimpleDateFormat(inputPattern, Locale.getDefault()).parse(this)
    return calendar
}

val Context.layoutInflater: android.view.LayoutInflater
    get() = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as android.view.LayoutInflater

fun Calendar.getRestDate(): String{

    return SimpleDateFormat(Parameters.REST_DATE, Locale.getDefault()).let {
        it.timeZone = TimeZone.getDefault()
        it.format(this.time)
    }
}

fun Calendar.getSDFdate(outputPattern: String = "yyyy-MM-dd HH:mm"): String{

    return SimpleDateFormat(outputPattern, Locale.getDefault()).let {
        it.timeZone = TimeZone.getDefault()
        it.format(this.time)
    }
}

fun ListView.setListViewHeightBasedOnItems() : Boolean{

    val listAdapter = this.adapter
    if (listAdapter != null) {

        val numberOfItems = listAdapter.count
        var totalItemsHeight = 0
        for (itemPos in 0 until numberOfItems) {
            val item = listAdapter.getView(itemPos, null, this)
            item.measure(0, 0)
            totalItemsHeight += item.measuredHeight
        }
        val totalDividersHeight = this.dividerHeight *
                (numberOfItems - 1);

        val params = this.layoutParams
        params.height = totalItemsHeight + totalDividersHeight
        this.layoutParams = params
        this.requestLayout()

        return true;
    } else {
        return false;
    }

}

fun String.toUUID() =
    UUID.fromString(this)