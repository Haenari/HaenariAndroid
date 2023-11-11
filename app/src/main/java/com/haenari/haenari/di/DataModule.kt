package com.haenari.haenari.di

import androidx.datastore.core.DataStore
import com.haenari.haenari.Weather.WeatherData
import com.haenari.haenari.data.api.RetrofitCreator
import com.haenari.haenari.data.api.RetrofitProvider
import com.haenari.haenari.data.api.WeatherAPI
import com.haenari.haenari.data.dao.WeatherDao
import com.haenari.haenari.data.repository.weather.WeatherRepositoryImpl
import com.haenari.haenari.data.repository.weather.source.WeatherLocalDataSource
import com.haenari.haenari.data.repository.weather.source.WeatherRemoteDataSource
import com.haenari.haenari.domain.repository.weather.WeatherRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {
    @Singleton
    @Provides
    fun providesRetrofitCreator(): RetrofitProvider = RetrofitCreator.instance

    @Singleton
    @Provides
    fun providesWeatherAPI(): WeatherAPI = providesRetrofitCreator().weatherAPI()

    /**
     * Weather
     */
    @Singleton
    @Provides
    fun providesWeatherRemoteDataSource(api: WeatherAPI) = WeatherRemoteDataSource(api)

    @Singleton
    @Provides
    fun providesWeatherLocalDataSource(
        protoDataStore: DataStore<WeatherData>,
        dao: WeatherDao
    ): WeatherLocalDataSource = WeatherLocalDataSource(protoDataStore, dao)

    @Singleton
    @Provides
    fun providesWeatherRepository(
        localDataSource: WeatherLocalDataSource,
        remoteDataSource: WeatherRemoteDataSource
    ): WeatherRepository =
        WeatherRepositoryImpl(localDataSource, remoteDataSource)
}