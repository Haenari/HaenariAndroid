package com.haenari.haenari.presentation.views.main

sealed class MainEvent {
    object None : MainEvent()
    object ButtonClick: MainEvent()
    data class ReceivedLocation(
        val latLng: Pair<Int, Int>,
        val address: String
    ): MainEvent()
}