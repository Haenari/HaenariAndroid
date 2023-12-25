package com.haenari.haenari.data.repository.weather.source

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.haenari.haenari.Weather.WeatherData
import com.haenari.haenari.data.dao.WeatherDao
import com.haenari.haenari.data.datastore.PreferencesKey
import com.haenari.haenari.data.entity.WeatherEntity
import com.haenari.haenari.data.model.weather.MidTermLandItem
import com.haenari.haenari.data.model.weather.MidTermTemperatureItem
import com.haenari.haenari.data.model.weather.ShortTermItem
import com.haenari.haenari.data.repository.weather.WeatherMapper.toWeatherEntity
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import org.joda.time.DateTime
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WeatherLocalDataSource @Inject constructor(
    private val prefDataStore: DataStore<Preferences>,
    private val protoDataStore: DataStore<WeatherData>,
    private val dao: WeatherDao
) {
    suspend fun readShortTermCachingTime(): Long {
        return prefDataStore.data.map { pref ->
            pref[PreferencesKey.SHORT_TERM_CACHING_TIME]
        }.first() ?: 0L
    }

    suspend fun updateShortTermCachingTime(millis: Long) {
        prefDataStore.edit { pref ->
            pref[PreferencesKey.SHORT_TERM_CACHING_TIME] = millis
        }
    }

    suspend fun readMidTermLandCachingTime(): Long {
        return prefDataStore.data.map { pref ->
            pref[PreferencesKey.MID_TERM_LAND_CACHING_TIME]
        }.first() ?: 0L
    }

    suspend fun updateMidTermLandCachingTime(millis: Long) {
        prefDataStore.edit { pref ->
            pref[PreferencesKey.MID_TERM_LAND_CACHING_TIME] = millis
        }
    }

    suspend fun readMidTermTemperatureCachingTime(): Long {
        return prefDataStore.data.map { pref ->
            pref[PreferencesKey.MID_TERM_TEMPERATURE_CACHING_TIME]
        }.first() ?: 0L
    }

    suspend fun updateMidTermTemperatureCachingTime(millis: Long) {
        prefDataStore.edit { pref ->
            pref[PreferencesKey.MID_TERM_TEMPERATURE_CACHING_TIME] = millis
        }
    }

    suspend fun updateShortTerm(items: List<ShortTermItem>): Boolean {
        val result = items.toWeatherEntity()
        return dao.insertWeatherList(result).any { it >= 0 }
    }

    suspend fun updateMidTermLand(
        item: MidTermLandItem,
        startDate: String = DateTime.now().plusDays(3).toString("yyyyMMdd"),
        endDate: String = DateTime.now().plusDays(7).toString("yyyyMMdd")
    ): Boolean {
        val result = item.toWeatherEntity()
        return dao.insertOrUpdateWeatherList(
            entities = result,
            startDate = startDate,
            endDate = endDate
        ).any { it >= 0 }
    }

    suspend fun updateMidTermTemperature(
        item: MidTermTemperatureItem,
        startDate: String = DateTime.now().plusDays(3).toString("yyyyMMdd"),
        endDate: String = DateTime.now().plusDays(7).toString("yyyyMMdd")
    ): Boolean {
        val result = item.toWeatherEntity()
        return dao.insertOrUpdateWeatherList(
            entities = result,
            startDate = startDate,
            endDate = endDate
        ).any { it >= 0 }
    }

    fun readDailyWeather(date: String): WeatherEntity {
        return dao.selectWeather(date)
    }

    fun readWeeklyWeather(startDate: String, endDate: String): List<WeatherEntity> {
        return dao.selectWeather(startDate, endDate)
    }

    fun readAllWeather(): List<WeatherEntity> {
        return dao.selectWeather()
    }
}