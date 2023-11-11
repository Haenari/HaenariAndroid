package com.haenari.haenari.data.repository.weather

import com.haenari.haenari.data.repository.weather.source.WeatherLocalDataSource
import com.haenari.haenari.data.repository.weather.source.WeatherRemoteDataSource
import com.haenari.haenari.domain.repository.weather.WeatherRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WeatherRepositoryImpl @Inject constructor(
    private val localDataSource: WeatherLocalDataSource,
    private val remoteDataSource: WeatherRemoteDataSource
): WeatherRepository {

}