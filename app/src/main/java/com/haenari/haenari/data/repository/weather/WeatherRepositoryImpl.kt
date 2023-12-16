package com.haenari.haenari.data.repository.weather

import com.haenari.haenari.data.entity.WeatherEntity
import com.haenari.haenari.data.repository.weather.source.WeatherLocalDataSource
import com.haenari.haenari.data.repository.weather.source.WeatherRemoteDataSource
import com.haenari.haenari.data.util.checkIsSuccess
import com.haenari.haenari.domain.repository.weather.WeatherRepository
import org.joda.time.DateTime
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WeatherRepositoryImpl @Inject constructor(
    private val localDataSource: WeatherLocalDataSource,
    private val remoteDataSource: WeatherRemoteDataSource
) : WeatherRepository {
    // todo api 별로 read & local update + caching time -> result = Boolean
    // todo 실제 사용할 데이터 꺼내올 함수 따로 만들기

    override suspend fun syncShortTerm(date: String, time: String, nx: Int, ny: Int): Boolean {
        val response = remoteDataSource.getShortTerm(date, time, nx, ny)
        return response.checkIsSuccess { entity ->
            localDataSource.updateShortTerm(entity.getItems()).also { result ->
                if (result) {
                    localDataSource.updateShortTermCachingTime(DateTime.now().millis)
                }
            }
        }
    }

    override suspend fun syncMidTermLand(regId: String, tmFc: String): Boolean {
        val response = remoteDataSource.getMidTermLand(regId, tmFc)
        return response.checkIsSuccess { entity ->
            entity.getItem()?.let {
                localDataSource.updateMidTermLand(it)
            } ?: false
        }
    }

    override suspend fun syncMidTermTemperature(regId: String, tmFc: String): Boolean {
        val response = remoteDataSource.getMidTermTemperature(regId, tmFc)
        return response.checkIsSuccess { entity ->
            entity.getItem()?.let {
                localDataSource.updateMidTermTemperature(it)
            } ?: false
        }
    }

    //todo for test
    override fun readAllWeather(): List<WeatherEntity> {
        return localDataSource.readAllWeather()
    }
}