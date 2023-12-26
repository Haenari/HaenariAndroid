package com.haenari.haenari.domain.usecase.weather

import com.haenari.haenari.domain.repository.weather.WeatherRepository
import javax.inject.Inject

class SyncShortTermUseCase @Inject constructor(
    private val weatherRepository: WeatherRepository
): suspend (String, String, Int, Int) -> Boolean {
    override suspend fun invoke(date: String, time: String, nx: Int, ny: Int): Boolean {
        // todo recursion
        return weatherRepository.syncShortTerm(date, time, nx, ny)
    }
}