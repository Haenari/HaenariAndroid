package com.haenari.haenari.presentation.util

import org.joda.time.DateTime

object DateTimes {
    fun date(dateTime: DateTime): String = dateTime.toString("yyyyMMdd")

    fun time(dateTime: DateTime): String = dateTime.toString("HHmm")
}