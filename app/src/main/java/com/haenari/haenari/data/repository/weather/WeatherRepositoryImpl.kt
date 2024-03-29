package com.haenari.haenari.data.repository.weather

import com.haenari.haenari.LegacyUtil.checkCaching
import com.haenari.haenari.NotNormalServiceException
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
        val cachingTime = localDataSource.readShortTermCachingTime()

        return if (cachingTime.checkCaching()) {
            true
        } else {
            val response = remoteDataSource.getShortTerm(date, time, nx, ny)
            response.checkIsSuccess { entity ->
                if(entity.response?.header?.resultCode != "00") {
                    throw NotNormalServiceException(entity.response?.header?.resultCode)
                } else {
                    localDataSource.updateShortTerm(entity.getItems()).also { result ->
                        if (result) {
                            localDataSource.updateShortTermCachingTime(DateTime.now().millis)
                        }
                    }
                }
            }
        }
    }

    override suspend fun syncMidTermLand(regId: String, tmFc: String): Boolean {
        val cachingTime = localDataSource.readMidTermLandCachingTime()

        return if (cachingTime.checkCaching()) {
            true
        } else {
            val response = remoteDataSource.getMidTermLand(regId, tmFc)
            response.checkIsSuccess { entity ->
                if(entity.response?.header?.resultCode != "00") {
                    throw NotNormalServiceException(entity.response?.header?.resultCode)
                } else {
                    localDataSource.updateMidTermLand(entity.getItem()).also { result ->
                        if (result) {
                            localDataSource.updateMidTermLandCachingTime(DateTime.now().millis)
                        }
                    }
                }
            }
        }
    }

    override suspend fun syncMidTermTemperature(regId: String, tmFc: String): Boolean {
        val cachingTime = localDataSource.readMidTermTemperatureCachingTime()

        return if (cachingTime.checkCaching()) {
            true
        } else {
            val response = remoteDataSource.getMidTermTemperature(regId, tmFc)
            response.checkIsSuccess { entity ->
                if(entity.response?.header?.resultCode != "00") {
                    throw NotNormalServiceException(entity.response?.header?.resultCode)
                } else {
                    localDataSource.updateMidTermTemperature(entity.getItem()).also { result ->
                        if (result) {
                            localDataSource.updateMidTermTemperatureCachingTime(DateTime.now().millis)
                        }
                    }
                }
            }
        }
    }

    override suspend fun getDailyWeather(date: String): WeatherEntity {
        return localDataSource.readDailyWeather(date)
    }

    override suspend fun getWeeklyWeather(startDate: String, endDate: String): List<WeatherEntity> {
        return localDataSource.readWeeklyWeather(startDate, endDate)
    }

    //todo for test
    override fun readAllWeather(): List<WeatherEntity> {
        return localDataSource.readAllWeather()
    }
}