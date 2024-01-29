package com.haenari.haenari.presentation.views.main

data class MainState(
    val isBtnClicked: Boolean,
    val isReceivedLocation: Boolean,
    val isLoading: Boolean,
    val address: String
)
