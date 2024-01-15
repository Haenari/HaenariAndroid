package com.haenari.haenari.presentation.views.home

import androidx.lifecycle.viewModelScope
import com.haenari.haenari.AppConstants
import com.haenari.haenari.data.entity.WeatherEntity
import com.haenari.haenari.domain.usecase.weather.ReadDailyWeatherUseCase
import com.haenari.haenari.presentation.base.viewmodel.BaseViewModel
import com.haenari.haenari.presentation.util.DateTimes.date
import com.haenari.haenari.presentation.util.Locations
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import org.joda.time.DateTime
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val readDailyWeatherUseCase: ReadDailyWeatherUseCase
) : BaseViewModel<HomeEvent, HomeState>() {
    override var currentEvent: HomeEvent = HomeEvent.None
    override val state: StateFlow<HomeState> = initState(
        HomeState(
            latLng = (Locations.DEFAULT_LATITUDE to Locations.DEFAULT_LONGITUDE),
            address = AppConstants.DEFAULT_ADDRESS,
            dailyWeather = WeatherEntity()
        )
    )

    override fun changeState(current: HomeState, event: HomeEvent): HomeState {
        return when (event) {
            is HomeEvent.None -> {
                current.copy()
            }

            is HomeEvent.ReceivedLocation -> {
                current.copy(latLng = event.latLng, address = event.address)
            }

            is HomeEvent.ReceivedWeather -> {
                current.copy(dailyWeather = event.dailyWeather)
            }
        }
    }

    fun receivedLocation(latLng: Pair<Double, Double>, address: String) {
        viewModelScope.launch {
            onEvent(HomeEvent.ReceivedLocation(latLng = latLng, address = address))
            requestDailyWeather()
        }
    }

    private fun requestDailyWeather() {
        viewModelScope.launch(Dispatchers.IO) {
            readDailyWeatherUseCase.invoke(DateTime.now().date())
                .catch {

                }.collect { result ->
                    result?.let {
                        onEvent(HomeEvent.ReceivedWeather(result))
                    }
                }
        }
    }
}