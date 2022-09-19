package com.adhiambo.movieguide.presentation

import java.text.SimpleDateFormat
import java.util.*

object UiUtils {

    fun String.getYear(): String {
        val inputFormat = SimpleDateFormat(
            "yyyy-MM-dd",
            Locale.getDefault()
        )
        val parseDate = inputFormat.parse(this)

        val outPutFormat = SimpleDateFormat(
            "yyyy",
            Locale.getDefault()
        )

        return outPutFormat.format(parseDate)
    }
}