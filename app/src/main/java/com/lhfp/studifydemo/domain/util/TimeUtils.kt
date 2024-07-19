package com.lhfp.studifydemo.domain.util

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class TimeUtils {
    companion object {
        fun getStringDateOf(millis: Long): String {
            return SimpleDateFormat("dd/MM", Locale.getDefault()).format(Date(millis))
        }
    }
}