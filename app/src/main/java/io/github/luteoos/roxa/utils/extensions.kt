package io.github.luteoos.roxa.utils

import android.widget.ListView
import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.*


fun String.getFormattedDate(outputPattern: String = "yyyy-MM-dd HH:mm"): String {
    var formattedDateString = ""
    try {
        val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ", Locale.getDefault())
        sdf.timeZone = TimeZone.getDefault()
        sdf.parse(this)?.let {
            formattedDateString = SimpleDateFormat(outputPattern, Locale.getDefault()).format(it)
        }
    } catch (e: Exception) {
        Timber.e(e)
    }
    return formattedDateString
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