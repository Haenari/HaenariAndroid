package com.haenari.haenari.domain.usecase.weather

import com.haenari.haenari.data.entity.WeatherEntity
import com.haenari.haenari.domain.repository.weather.WeatherRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ReadDailyWeatherUseCase @Inject constructor(
    private val weatherRepository: WeatherRepository
) : suspend (String) -> Flow<WeatherEntity> {
    override suspend fun invoke(date: String): Flow<WeatherEntity> {
        return flow {
            emit(weatherRepository.getDailyWeather(date))
        }
    }
}