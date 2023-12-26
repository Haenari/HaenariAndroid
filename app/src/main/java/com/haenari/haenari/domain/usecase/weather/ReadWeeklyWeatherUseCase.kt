package com.haenari.haenari.domain.usecase.weather

import com.haenari.haenari.data.entity.WeatherEntity
import com.haenari.haenari.domain.repository.weather.WeatherRepository
import javax.inject.Inject

class ReadWeeklyWeatherUseCase @Inject constructor(
    private val weatherRepository: WeatherRepository
) : suspend (String, String) -> List<WeatherEntity> {
    override suspend fun invoke(startDate: String, endDate: String): List<WeatherEntity> {
        return weatherRepository.getWeeklyWeather(startDate, endDate)
    }
}