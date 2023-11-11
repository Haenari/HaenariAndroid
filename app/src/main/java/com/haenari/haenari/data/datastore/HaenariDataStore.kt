package com.haenari.haenari.data.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.Serializer
import androidx.datastore.dataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.haenari.haenari.Weather.WeatherData
import java.io.InputStream
import java.io.OutputStream

/**
 * File Name
 */
const val SAMPLE_PREFERENCES_NAME = "sample_preferences"
const val WEATHER_PREFERENCES_NAME = "weather_preferences"

object PreferencesKey {
    val SAMPLE_STRING = "sample_string"

    /**
     * weather
     */
    val WEATHER_SAVE_TIME = "weather_save_time"
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
val Context.weatherProtoDataStore: DataStore<WeatherData> by dataStore(fileName = "weather.proto", serializer = WeatherDataSerializer)