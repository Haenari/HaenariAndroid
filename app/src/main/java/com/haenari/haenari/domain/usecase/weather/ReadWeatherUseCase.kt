package com.haenari.haenari.domain.usecase.weather

import com.haenari.haenari.data.entity.WeatherEntity
import com.haenari.haenari.domain.repository.weather.WeatherRepository
import javax.inject.Inject

class ReadWeatherUseCase @Inject constructor(
    private val weatherRepository: WeatherRepository
): () -> List<WeatherEntity> {
    override fun invoke(): List<WeatherEntity> {
        return weatherRepository.readAllWeather()
    }
}