package com.haenari.haenari.presentation.util

import org.joda.time.DateTime

object DateTimes {
    fun DateTime.date(): String = this.toString("yyyyMMdd")

    fun time(dateTime: DateTime): String = dateTime.toString("HHmm")
}