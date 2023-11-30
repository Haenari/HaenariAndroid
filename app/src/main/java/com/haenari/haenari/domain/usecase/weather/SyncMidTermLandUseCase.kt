package com.haenari.haenari.domain.usecase.weather

import com.haenari.haenari.domain.repository.weather.WeatherRepository
import javax.inject.Inject

class SyncMidTermLandUseCase @Inject constructor(
    private val weatherRepository: WeatherRepository
) {
}