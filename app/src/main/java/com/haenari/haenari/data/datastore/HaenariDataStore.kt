package com.haenari.haenari.data.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.Serializer
import androidx.datastore.dataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.haenari.haenari.Weather.WeatherData
import java.io.InputStream
import java.io.OutputStream

/**
 * File Name
 */
const val SAMPLE_PREFERENCES_NAME = "sample_preferences"
const val WEATHER_PREFERENCES_NAME = "weather_preferences"

const val WEATHER_PROTO_FILE_NAME = "weather.proto"

object PreferencesKey {
    val SAMPLE_STRING = "sample_string"

    /**
     * weather
     */
    val WEATHER_SAVE_TIME = "weather_save_time"

    val SHORT_TERM_CACHING_TIME = longPreferencesKey("short_term_caching_time")
    val MID_TERM_LAND_CACHING_TIME = longPreferencesKey("mid_term_land_caching_time")
    val MID_TERM_TEMPERATURE_CACHING_TIME = longPreferencesKey("mid_term_temperature_caching_time")
}

object WeatherDataSerializer : Serializer<WeatherData> {
    override val defaultValue: WeatherData = WeatherData.getDefaultInstance().toBuilder()
        .build()

    override suspend fun readFrom(input: InputStream): WeatherData =
        WeatherData.parseFrom(input)


    override suspend fun writeTo(t: WeatherData, output: OutputStream) =
        t.writeTo(output)
}

/**
 * preferences DataStore
 */
val Context.samplePreferencesDataStore: DataStore<Preferences> by preferencesDataStore(name = SAMPLE_PREFERENCES_NAME)
val Context.weatherPreferencesDataStore: DataStore<Preferences> by preferencesDataStore(name = WEATHER_PREFERENCES_NAME)

/**
 * proto DataStore
 */

val Context.weatherProtoDataStore: DataStore<WeatherData> by dataStore(
    fileName = WEATHER_PROTO_FILE_NAME,
    serializer = WeatherDataSerializer
)
