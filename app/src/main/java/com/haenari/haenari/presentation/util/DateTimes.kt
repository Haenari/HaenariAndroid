package com.haenari.haenari.presentation.util

import org.joda.time.DateTime

object DateTimes {
    fun DateTime.date(): String = this.toString("yyyyMMdd")

    fun DateTime.time(): String = this.toString("HHmm")

    fun midTermWeatherTime(): String {
        return if (DateTime.now().hourOfDay >= 18) {
            DateTime.now().withTimeAtStartOfDay().plusHours(18).toString("yyyyMMddHHmm")
        } else if (DateTime.now().hourOfDay >= 6) {
            DateTime.now().withTimeAtStartOfDay().plusHours(6).toString("yyyyMMddHHmm")
        } else {
            DateTime.now().withTimeAtStartOfDay().minusHours(6).toString("yyyyMMddHHmm")
        }
    }

    fun getDayOfWeekKrStr(dayOfWeek: Int): String {
        val list = listOf(" ", "월", "화", "수", "목", "금", "토", "일")
        return "${list[dayOfWeek]}요일"
    }
}