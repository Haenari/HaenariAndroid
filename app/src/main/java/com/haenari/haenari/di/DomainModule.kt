package com.haenari.haenari.di

import com.haenari.haenari.domain.repository.weather.WeatherRepository
import com.haenari.haenari.domain.usecase.weather.SyncMidTermLandUseCase
import com.haenari.haenari.domain.usecase.weather.SyncMidTermTemperatureUseCase
import com.haenari.haenari.domain.usecase.weather.SyncShortTermUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DomainModule {

    @Singleton
    @Provides
    fun providesSyncShortTermUseCase(weatherRepository: WeatherRepository) =
        SyncShortTermUseCase(weatherRepository)

    @Singleton
    @Provides
    fun providesSyncMidTermLandUseCase(weatherRepository: WeatherRepository) =
        SyncMidTermLandUseCase(weatherRepository)

    @Singleton
    @Provides
    fun providesSyncMidTermTemperatureUseCase(weatherRepository: WeatherRepository) =
        SyncMidTermTemperatureUseCase(weatherRepository)

}