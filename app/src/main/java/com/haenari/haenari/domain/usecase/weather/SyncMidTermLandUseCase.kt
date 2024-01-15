package com.haenari.haenari.domain.usecase.weather

import com.haenari.haenari.domain.repository.weather.WeatherRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SyncMidTermLandUseCase @Inject constructor(
    private val weatherRepository: WeatherRepository
) : suspend (String, String) -> Flow<Boolean> {
    override suspend fun invoke(regId: String, tmFc: String): Flow<Boolean> {
        // todo recursion
        return flow {
            emit(weatherRepository.syncMidTermLand(regId = regId, tmFc = tmFc))
        }
    }
}