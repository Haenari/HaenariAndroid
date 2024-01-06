package com.haenari.haenari.presentation.base

interface Splash {
    fun initSplash(nextProcess: (() -> Unit)?)
    fun startSplashScreen()
    fun setOnSplashScreen(nextProcess: (() -> Unit)?)
}