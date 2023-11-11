package com.haenari.haenari.di

import com.haenari.haenari.data.api.RetrofitCreator
import com.haenari.haenari.data.api.RetrofitProvider
import com.haenari.haenari.data.api.WeatherAPI
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
}