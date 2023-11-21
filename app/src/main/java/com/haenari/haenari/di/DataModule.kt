package com.haenari.haenari.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.haenari.haenari.Weather.WeatherData
import com.haenari.haenari.data.api.RetrofitCreator
import com.haenari.haenari.data.api.RetrofitProvider
import com.haenari.haenari.data.api.WeatherAPI
import com.haenari.haenari.data.dao.WeatherDao
import com.haenari.haenari.data.database.HaenariDatabase
import com.haenari.haenari.data.database.RoomDaoProvider
import com.haenari.haenari.data.datastore.weatherPreferencesDataStore
import com.haenari.haenari.data.datastore.weatherProtoDataStore
import com.haenari.haenari.data.repository.weather.WeatherRepositoryImpl
import com.haenari.haenari.data.repository.weather.source.WeatherLocalDataSource
import com.haenari.haenari.data.repository.weather.source.WeatherRemoteDataSource
import com.haenari.haenari.domain.repository.weather.WeatherRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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

    @Singleton
    @Provides
    fun providesRoomDaoProvider(@ApplicationContext context: Context): RoomDaoProvider =
        HaenariDatabase.create(context)

    @Singleton
    @Provides
    fun providesWeatherDao(roomDaoProvider: RoomDaoProvider): WeatherDao =
        roomDaoProvider.weatherDao()

    @Singleton
    @Provides
    fun providesWeatherPreferencesDataStore(
        @ApplicationContext context: Context
    ): DataStore<Preferences> =
        context.weatherPreferencesDataStore

    @Singleton
    @Provides
    fun providesWeatherProtoDataStore(
        @ApplicationContext context: Context
    ): DataStore<WeatherData> =
        context.weatherProtoDataStore

    /**
     * Weather
     */
    @Singleton
    @Provides
    fun providesWeatherRemoteDataSource(api: WeatherAPI) = WeatherRemoteDataSource(api)

    @Singleton
    @Provides
    fun providesWeatherLocalDataSource(
        prefDataStore: DataStore<Preferences>,
        protoDataStore: DataStore<WeatherData>,
        dao: WeatherDao
    ): WeatherLocalDataSource = WeatherLocalDataSource(prefDataStore, protoDataStore, dao)

    @Singleton
    @Provides
    fun providesWeatherRepository(
        localDataSource: WeatherLocalDataSource,
        remoteDataSource: WeatherRemoteDataSource
    ): WeatherRepository =
        WeatherRepositoryImpl(localDataSource, remoteDataSource)
}