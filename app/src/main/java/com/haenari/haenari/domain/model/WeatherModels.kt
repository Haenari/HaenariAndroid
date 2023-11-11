package com.haenari.haenari.domain.model

data class TodayWeather(
    val minTemperature: Float,
    val maxTemperature: Float,
    val skyStatus: Int
)

data class FutureWeathers(
    val lists: List<FutureWeather>
)

data class FutureWeather(
    val minTemperature: Int,
    val maxTemperature: Int,
    val skyStatus: String,
    val probabilityOfPrecipitation: Int
)
