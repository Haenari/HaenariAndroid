package com.haenari.haenari.presentation.views.weather

import androidx.lifecycle.viewModelScope
import com.haenari.haenari.AppConstants
import com.haenari.haenari.domain.usecase.weather.ReadWeeklyWeatherUseCase
import com.haenari.haenari.presentation.base.viewmodel.BaseViewModel
import com.haenari.haenari.presentation.util.DateTimes.date
import com.haenari.haenari.presentation.util.Locations
import com.haenari.haenari.presentation.util.Logs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.joda.time.DateTime
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val readWeeklyWeatherUseCase: ReadWeeklyWeatherUseCase
) : BaseViewModel<WeatherEvent, WeatherState>() {
    override var currentEvent: WeatherEvent = WeatherEvent.None
    override val state: StateFlow<WeatherState> = initState(
        WeatherState(
            address = AppConstants.DEFAULT_ADDRESS,
            weeklyWeather = emptyList()
        )
    )

    override fun changeState(current: WeatherState, event: WeatherEvent): WeatherState {
        return when (event) {
            is WeatherEvent.None -> {
                current.copy()
            }

            is WeatherEvent.ReceivedLocation -> {
                current.copy(address = event.address)
            }

            is WeatherEvent.ReceivedWeather -> {
                current.copy(weeklyWeather = event.weeklyWeather)
            }
        }
    }

    fun receivedLocation(address: String) {
        viewModelScope.launch {
            onEvent(WeatherEvent.ReceivedLocation(address = address))
            requestWeather()
        }
    }

    private fun requestWeather() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = readWeeklyWeatherUseCase.invoke(
                DateTime.now().plusDays(1).date(),
                DateTime.now().plusDays(7).date()
            )
            onEvent(WeatherEvent.ReceivedWeather(result))
        }
    }
}