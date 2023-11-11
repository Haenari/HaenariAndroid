package com.haenari.haenari.presentation.views.main

sealed class MainEvent {
    object None : MainEvent()
    object ButtonClick: MainEvent()
}