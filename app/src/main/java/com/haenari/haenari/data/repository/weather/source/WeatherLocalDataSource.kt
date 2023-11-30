package com.haenari.haenari.data.repository.weather.source

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.haenari.haenari.Weather.WeatherData
import com.haenari.haenari.data.dao.WeatherDao
import com.haenari.haenari.data.datastore.PreferencesKey
import com.haenari.haenari.data.entity.Weather
import com.haenari.haenari.data.entity.WeatherEntity
import com.haenari.haenari.data.model.weather.MidTermLandEntity
import com.haenari.haenari.data.model.weather.MidTermTemperatureEntity
import com.haenari.haenari.data.model.weather.ShortTermItem
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
        val result = items.groupBy {
            it.fcstDate
        }.map {
            val date = it.key
            val list = it.value.groupBy { it.fcstTime }

            date to list
        }.map { firstMap ->
            val date = checkNotNull(firstMap.first)
            val mutableList = MutableList(24) { Weather.defaultValue() }

            firstMap.second.map { secondMap ->
                val time = secondMap.key
                val index = checkNotNull(time).toInt() / 100
                val list = secondMap.value

                var temperature = Float.MAX_VALUE
                var skyStatus = ""
                var probabilityOfPrecipitation = Int.MAX_VALUE
                list.map {
                    when (it.category) {
                        "VVV" -> {
                            temperature = it.fcstValue?.toFloat() ?: Float.MAX_VALUE
                        }

                        "REH" -> {
                            probabilityOfPrecipitation = it.fcstValue?.toInt() ?: Int.MAX_VALUE
                        }

                        "PTY" -> {
                            skyStatus = it.fcstValue ?: ""
                        }
                    }
                }
                mutableList[index] = Weather(temperature, skyStatus, probabilityOfPrecipitation)
            }
            //Logs.e("result::${WeatherEntity(date, mutableList)}")
            WeatherEntity(date, mutableList)
        }

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