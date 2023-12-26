package com.haenari.haenari.presentation.views.main

import androidx.lifecycle.viewModelScope
import com.haenari.haenari.NotNormalServiceException
import com.haenari.haenari.domain.usecase.weather.ReadWeatherUseCase
import com.haenari.haenari.domain.usecase.weather.SyncMidTermLandUseCase
import com.haenari.haenari.domain.usecase.weather.SyncMidTermTemperatureUseCase
import com.haenari.haenari.domain.usecase.weather.SyncShortTermUseCase
import com.haenari.haenari.getMidTermLandCoordinate
import com.haenari.haenari.getMidTermTemperatureCoordinate
import com.haenari.haenari.presentation.base.viewmodel.BaseViewModel
import com.haenari.haenari.presentation.util.DateTimes
import com.haenari.haenari.presentation.util.DateTimes.date
import com.haenari.haenari.presentation.util.DateTimes.time
import com.haenari.haenari.presentation.util.Logs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.joda.time.DateTime
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val syncShortTermUseCase: SyncShortTermUseCase,
    private val syncMidTermTemperatureUseCase: SyncMidTermTemperatureUseCase,
    private val syncMidTermLandUseCase: SyncMidTermLandUseCase,
    private val readWeatherUseCase: ReadWeatherUseCase
) : BaseViewModel<MainEvent, MainState>() {
    override var currentEvent: MainEvent = MainEvent.None
    override val state: StateFlow<MainState> = initState(MainState(
        isBtnClicked = false,
        isReceivedLocation = false
    ))

    override fun changeState(current: MainState, event: MainEvent): MainState {
        return when (event) {
            is MainEvent.ButtonClick -> {
                current.copy(isBtnClicked = true)
            }

            is MainEvent.None -> {
                current.copy(isBtnClicked = false)
            }

            is MainEvent.ReceivedLocation -> {
                current.copy()
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

    fun requestWeather(coordinate: Pair<Int, Int>, address: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val nx = coordinate.first
            val ny = coordinate.second
            val shortTerm = getShortTerm(DateTime.now(), nx, ny, 0)
                //syncShortTermUseCase.invoke(DateTime.now().date(), DateTime.now().time(), nx, ny)
            val midTermLand = syncMidTermLandUseCase.invoke(
                regId = getMidTermLandCoordinate(address),
                tmFc = DateTimes.midTermWeatherTime()
            )
            val midTermTemp = syncMidTermTemperatureUseCase.invoke(
                regId = getMidTermTemperatureCoordinate(address),
                tmFc = DateTimes.midTermWeatherTime()
            )

            val result = readWeatherUseCase.invoke()
//            Logs.e("shortTerm :: $shortTerm")
//            Logs.e("midTermTemp :: $midTermTemp")
//            Logs.e("midTermLand :: $midTermLand")
//            result.map {
//                Logs.e(it.toText())
//            }
        }
    }

    private suspend fun getShortTerm(dateTime: DateTime, nx: Int, ny: Int, recallCount: Int): Boolean {
        val date = dateTime.minusHours(recallCount).date()
        val time = dateTime.minusHours(recallCount).time()
        return if(recallCount > 6) false
        else {
            try {
                syncShortTermUseCase(date, time, nx, ny)
            } catch (exception: NotNormalServiceException) {
                getShortTerm(dateTime, nx, ny, recallCount.plus(1))
            }
        }
    }
}