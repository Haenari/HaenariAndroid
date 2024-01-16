package com.haenari.haenari.presentation.views.home

import com.haenari.haenari.data.entity.WeatherEntity

sealed class HomeEvent {
    object None : HomeEvent()
    data class ReceivedLocation(val address: String) : HomeEvent()
    data class ReceivedWeather(val dailyWeather: WeatherEntity) : HomeEvent()
}