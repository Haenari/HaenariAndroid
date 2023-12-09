package com.haenari.haenari.data.repository.weather.source

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.haenari.haenari.Weather.WeatherData
import com.haenari.haenari.data.dao.WeatherDao
import com.haenari.haenari.data.datastore.PreferencesKey
import com.haenari.haenari.data.entity.WeatherEntity
import com.haenari.haenari.data.model.weather.MidTermLandEntity
import com.haenari.haenari.data.model.weather.MidTermTemperatureEntity
import com.haenari.haenari.data.model.weather.ShortTermItem
import com.haenari.haenari.data.repository.weather.WeatherMapper.toWeatherEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WeatherLocalDataSource @Inject constructor(
    private val prefDataStore: DataStore<Preferences>,
    private val protoDataStore: DataStore<WeatherData>,
    private val dao: WeatherDao
) {

    suspend fun updateShortTermCachingTime(millis: Long) {
        prefDataStore.edit { pref ->
            pref[PreferencesKey.SHORT_TERM_CACHING_TIME] = millis
        }
    }

    suspend fun updateMidTermLandCachingTime(millis: Long) {
        prefDataStore.edit { pref ->
            pref[PreferencesKey.MID_TERM_LAND_CACHING_TIME] = millis
        }
    }

    suspend fun updateMidTermTemperatureCachingTime(millis: Long) {
        prefDataStore.edit { pref ->
            pref[PreferencesKey.MID_TERM_TEMPERATURE_CACHING_TIME] = millis
        }
    }

    fun updateShortTerm(items: List<ShortTermItem>) {
        val result = items.toWeatherEntity()

        dao.insertWeatherList(result)
    }

    fun updateMidTermLand(entity: MidTermLandEntity) {

    }

    fun updateMidTermTemperature(entity: MidTermTemperatureEntity) {

    }

    fun readAllWeather(): List<WeatherEntity> {
        return dao.selectWeather()
    }
}