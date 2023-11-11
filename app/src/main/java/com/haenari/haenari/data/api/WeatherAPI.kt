package com.haenari.haenari.data.api

import com.haenari.haenari.BuildConfig
import com.haenari.haenari.data.model.weather.MidTermLandEntity
import com.haenari.haenari.data.model.weather.MidTermTemperatureEntity
import com.haenari.haenari.data.model.weather.ShortTermEntity
import com.haenari.haenari.data.model.weather.UltraShortTermEntity
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherAPI {
    companion object {
        const val SHORT_TERM = "VilageFcstInfoService_2.0"
        const val MID_TERM = "MidFcstInfoService"
    }

    @GET("${SHORT_TERM}/getUltraSrtNcst")
    suspend fun getUltraShort(
        @Query("serviceKey") serviceKey: String = BuildConfig.WEATHER_SERVICE_KEY,
        @Query("dataType") dataType: String = "JSON",
        @Query("base_date") baseDate: String,
        @Query("base_time") baseTime: String,
        @Query("nx") nx: Int,
        @Query("ny") ny: Int
    ): Response<UltraShortTermEntity>

    @GET("${SHORT_TERM}/getVilageFcst")
    suspend fun getShortTerm(
        @Query("serviceKey") serviceKey: String = BuildConfig.WEATHER_SERVICE_KEY,
        @Query("pageNo") pageNo: Int = 1,
        @Query("numOfRows") numOfRows: Int = 10000,
        @Query("dataType") dataType: String = "JSON",
        @Query("base_date") baseDate: String,
        @Query("base_time") baseTime: String,
        @Query("nx") nx: Int,
        @Query("ny") ny: Int
    ): Response<ShortTermEntity>

    @GET("${MID_TERM}/getMidTa")
    suspend fun getMidTermTemperature(
        @Query("serviceKey") serviceKey: String = BuildConfig.WEATHER_SERVICE_KEY,
        @Query("pageNo") pageNo: Int = 1,
        @Query("numOfRows") numOfRows: Int = 10000,
        @Query("dataType") dataType: String = "JSON",
        @Query("regId") regId: String,
        @Query("tmFc") tmFc: String
    ): Response<MidTermTemperatureEntity>

    @GET("${MID_TERM}/getMidLandFcst")
    suspend fun getMidTermLand(
        @Query("serviceKey") serviceKey: String = BuildConfig.WEATHER_SERVICE_KEY,
        @Query("pageNo") pageNo: Int = 1,
        @Query("numOfRows") numOfRows: Int = 10000,
        @Query("dataType") dataType: String = "JSON",
        @Query("regId") regId: String,
        @Query("tmFc") tmFc: String
    ): Response<MidTermLandEntity>
}