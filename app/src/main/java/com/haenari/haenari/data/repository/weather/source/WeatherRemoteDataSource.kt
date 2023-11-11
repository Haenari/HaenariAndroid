package com.haenari.haenari.data.repository.weather.source

import com.haenari.haenari.data.api.WeatherAPI
import com.haenari.haenari.data.model.weather.MidTermLandEntity
import com.haenari.haenari.data.model.weather.MidTermTemperatureEntity
import com.haenari.haenari.data.model.weather.ShortTermEntity
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WeatherRemoteDataSource @Inject constructor(
    private val api: WeatherAPI
) {

    suspend fun getShortTerm(
        baseDate: String,
        baseTime: String,
        nx: Int,
        ny: Int
    ): Response<ShortTermEntity> {
        return api.getShortTerm(
            baseDate = baseDate,
            baseTime = baseTime,
            nx = nx,
            ny = ny
        )
    }

    suspend fun getMidTermTemperature(
        regId: String,
        tmFc: String
    ): Response<MidTermTemperatureEntity> {
        return api.getMidTermTemperature(
            regId = regId,
            tmFc = tmFc
        )
    }

    suspend fun getMidTermLand(
        regId: String,
        tmFc: String
    ): Response<MidTermLandEntity> {
        return api.getMidTermLand(
            regId = regId,
            tmFc = tmFc
        )
    }
}