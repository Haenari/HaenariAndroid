package com.haenari.haenari.presentation.views.weather

import com.haenari.haenari.data.entity.WeatherEntity
import com.haenari.haenari.presentation.views.home.HomeEvent

sealed class WeatherEvent {

    object None : WeatherEvent()
    data class ReceivedLocation(val latLng: Pair<Double, Double>, val address: String) : WeatherEvent()

    data class ReceivedWeather(val weeklyWeather: List<WeatherEntity>) : WeatherEvent()
}