package com.haenari.haenari.data.repository.weather.source

import androidx.datastore.core.DataStore
import com.haenari.haenari.Weather.WeatherData
import com.haenari.haenari.data.dao.WeatherDao
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WeatherLocalDataSource @Inject constructor(
    private val protoDataStore: DataStore<WeatherData>,
    private val dao: WeatherDao
) {
}