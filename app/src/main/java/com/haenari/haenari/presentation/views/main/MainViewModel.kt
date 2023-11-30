package com.haenari.haenari.presentation.views.main

import androidx.lifecycle.viewModelScope
import com.haenari.haenari.domain.usecase.weather.ReadWeatherUseCase
import com.haenari.haenari.domain.usecase.weather.SyncShortTermUseCase
import com.haenari.haenari.presentation.base.viewmodel.BaseViewModel
import com.haenari.haenari.presentation.util.Logs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val syncShortTermUseCase: SyncShortTermUseCase,
    private val readWeatherUseCase: ReadWeatherUseCase
) : BaseViewModel<MainEvent, MainState>() {
    override var currentEvent: MainEvent = MainEvent.None
    override val state: StateFlow<MainState> = initState(MainState(false))

    override fun changeState(current: MainState, event: MainEvent): MainState {
        return when (event) {
            is MainEvent.ButtonClick -> {
                current.copy(isBtnClicked = true)
            }

            is MainEvent.None -> {
                current.copy(isBtnClicked = false)
            }
        }
    }

    // todo need to remove
    fun btnClick() {
        viewModelScope.launch {
            onEvent(MainEvent.ButtonClick)
            //onEvent(MainEvent.None)
        }
    }

    // todo need to remove
    fun test() {
        viewModelScope.launch(Dispatchers.IO) {
            val sync = syncShortTermUseCase.invoke("20231129", "2000", 55, 127)
            val result = readWeatherUseCase.invoke()
            Logs.e("sync :: $sync")
            Logs.e("result :: $result")
        }
    }
}