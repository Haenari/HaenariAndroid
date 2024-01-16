package com.haenari.haenari.presentation.views.weather

import com.haenari.haenari.data.entity.WeatherEntity

data class WeatherState(
    val address: String,
    val weeklyWeather: List<WeatherEntity>
)