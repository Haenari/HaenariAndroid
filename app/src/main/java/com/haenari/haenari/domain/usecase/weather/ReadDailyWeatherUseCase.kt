package com.haenari.haenari.domain.usecase.weather

import com.haenari.haenari.data.entity.WeatherEntity
import com.haenari.haenari.domain.repository.weather.WeatherRepository
import javax.inject.Inject

class ReadDailyWeatherUseCase @Inject constructor(
    private val weatherRepository: WeatherRepository
) : suspend (String) -> WeatherEntity {
    override suspend fun invoke(date: String): WeatherEntity {
        return weatherRepository.getDailyWeather(date)
    }
}