package com.haenari.haenari

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class HaenariApplication: Application() {

    override fun onCreate() {
        super.onCreate()
    }

    override fun onTerminate() {
        super.onTerminate()
    }
}