package com.haenari.haenari.presentation.views.main

import androidx.lifecycle.viewModelScope
import com.haenari.haenari.presentation.base.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(): BaseViewModel<MainEvent, MainState>() {
    override var currentEvent: MainEvent = MainEvent.None
    override val state: StateFlow<MainState> = initState(MainState(false))

    override fun changeState(current: MainState, event: MainEvent): MainState {
        return when(event) {
            is MainEvent.ButtonClick -> {
                current.copy(isBtnClicked = true)
            }
            is MainEvent.None -> {
                current.copy(isBtnClicked = false)
            }
        }
    }

    fun btnClick() {
        viewModelScope.launch {
            onEvent(MainEvent.ButtonClick)
            onEvent(MainEvent.None)
        }
    }
}