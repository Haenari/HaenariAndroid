package com.haenari.haenari.domain.repository.weather

import com.haenari.haenari.data.entity.WeatherEntity

interface WeatherRepository {
    suspend fun syncShortTerm(date: String, time: String, nx: Int, ny: Int): Boolean

    suspend fun syncMidTermLand(regId: String, tmFc: String): Boolean

    suspend fun syncMidTermTemperature(regId: String, tmFc: String): Boolean

    suspend fun getDailyWeather(date: String): WeatherEntity

    suspend fun getWeeklyWeather(startDate: String, endDate: String): List<WeatherEntity>

    // todo for test
    fun readAllWeather(): List<WeatherEntity>

}