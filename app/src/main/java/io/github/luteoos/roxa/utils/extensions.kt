package io.github.luteoos.roxa.utils

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

fun String.toUUID() =
    UUID.fromString(this)