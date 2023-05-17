package com.arqunn.usatoday.util

import android.text.format.DateUtils
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object DateUtils {
    private const val DEFAULT_DATE_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'"

    fun getRelativeTimeAgo(dateStr: String): String {
        val dateFormatter = SimpleDateFormat(DEFAULT_DATE_TIME_FORMAT, Locale.getDefault())
        var relativeDate = ""
        try {
            val dateMillis = dateFormatter.parse(dateStr)?.time ?: 0
            relativeDate = DateUtils.getRelativeTimeSpanString(
                dateMillis,
                System.currentTimeMillis(),
                DateUtils.HOUR_IN_MILLIS
            ).toString()
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return relativeDate
    }
}