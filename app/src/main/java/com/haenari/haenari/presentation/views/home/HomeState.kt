package com.haenari.haenari.presentation.views.home

import com.haenari.haenari.data.entity.WeatherEntity

data class HomeState (
    val latLng: Pair<Double, Double>,
    val address: String,
    val dailyWeather: WeatherEntity
)