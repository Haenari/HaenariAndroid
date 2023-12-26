package com.haenari.haenari.presentation.util

import android.content.Context
import com.haenari.haenari.R

object Weathers {

    private const val SKY_STATUS_SUNNY = 0
    private const val SKY_STATUS_RAINY = 1
    private const val SKY_STATUS_RAINY_SNOWY = 2
    private const val SKY_STATUS_SNOWY = 3
    private const val SKY_STATUS_SHOWER = 4

    fun getTodayString(context: Context, skyStatus: Int): String =
        when (skyStatus) {
            SKY_STATUS_SUNNY -> context.getString(
                R.string.weather_today,
                context.getString(R.string.weather_is_sunny)
            )

            SKY_STATUS_RAINY -> context.getString(
                R.string.weather_today,
                context.getString(R.string.weather_is_rainy)
            )

            SKY_STATUS_RAINY_SNOWY -> context.getString(
                R.string.weather_today,
                context.getString(R.string.weather_is_rainy_snowy)
            )

            SKY_STATUS_SNOWY -> context.getString(
                R.string.weather_today,
                context.getString(R.string.weather_is_snowy)
            )

            SKY_STATUS_SHOWER -> context.getString(
                R.string.weather_today,
                context.getString(R.string.weather_is_shower)
            )

            else -> context.getString(R.string.weather_is_none)
        }

    fun getString(context: Context, skyStatus: Int): String =
        when (skyStatus) {
            SKY_STATUS_SUNNY -> context.getString(R.string.weather_sunny)
            SKY_STATUS_RAINY -> context.getString(R.string.weather_rainy)
            SKY_STATUS_RAINY_SNOWY -> context.getString(R.string.weather_rainy_snowy)
            SKY_STATUS_SNOWY -> context.getString(R.string.weather_snowy)
            SKY_STATUS_SHOWER -> context.getString(R.string.weather_shower)
            else -> context.getString(R.string.weather_none)
        }

    fun getImage(skyStatus: Int): Int =
        when(skyStatus) {
            SKY_STATUS_SUNNY -> R.drawable.temp_ic_sunny
            SKY_STATUS_RAINY -> R.drawable.temp_ic_rain
            SKY_STATUS_RAINY_SNOWY -> R.drawable.temp_ic_sleet
            SKY_STATUS_SNOWY -> R.drawable.temp_ic_snow
            SKY_STATUS_SHOWER -> R.drawable.temp_ic_rain
            else -> R.drawable.temp_ic_error
        }
}