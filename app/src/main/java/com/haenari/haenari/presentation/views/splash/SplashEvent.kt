package com.haenari.haenari.presentation.views.splash

sealed class SplashEvent {
    object None : SplashEvent()
    data class SyncedWeather(val synced: Boolean, val address: String) : SplashEvent()
}