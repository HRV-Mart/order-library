package com.hrv.mart.orderlibrary.model.query

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

data class OrderDate (
    val year: Int,
    val month: Int,
    val day: Int,
) {
    fun parseToString(isStarting: Boolean): String {
        val time =
            if (isStarting) {
                "00:00:00"
            }
            else {
                "23:59:59"
            }
        return LocalDate.of(year, month, day).toString() + ":${time}"
    }
    companion object {
        fun getMaxDate() =
            toOrderDate(LocalDate.now())
        fun getMinDate() =
            toOrderDate(LocalDate.MIN)
        fun getDateTimeFormat() =
            DateTimeFormatter.ofPattern("yyyy-MM-dd:HH:mm:ss", Locale.ROOT)
        private fun toOrderDate(date: LocalDate) =
            OrderDate(
                year = maxOf(date.year, 1),
                month = date.month.value,
                day = date.dayOfMonth
            )
    }
}