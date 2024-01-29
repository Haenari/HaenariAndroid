package com.haenari.haenari.presentation.views.weather

import androidx.fragment.app.activityViewModels
import com.haenari.haenari.R
import com.haenari.haenari.databinding.FragmentWeatherBinding
import com.haenari.haenari.presentation.base.fragment.MVIFragment
import com.haenari.haenari.presentation.util.Logs
import com.haenari.haenari.presentation.util.Permissions
import com.haenari.haenari.presentation.views.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WeatherFragment :
    MVIFragment<FragmentWeatherBinding, WeatherEvent, WeatherState>(R.layout.fragment_weather) {
    companion object {
        fun newInstance() = WeatherFragment()
    }

    override val viewModel: WeatherViewModel by activityViewModels()

    private val recyclerAdapter = WeatherRecyclerAdapter()

    override fun render(state: WeatherState) {
        Logs.e("WeatherFragment::render::$state")
        bind {
            with (state) {
                tvLocation.text = address

                if(weeklyWeather.isNotEmpty()) {
                    recyclerAdapter.initItems(weeklyWeather)
                }
            }
        }
    }

    override fun initView() {
        bind {
            rvWeather.adapter = recyclerAdapter

            btnCurrentLocation.setOnClickListener {
                Permissions.requestLocationPermission(
                    onPermissionGranted = {
                        (activity as MainActivity).requestLocation()
                    },
                    onPermissionDenied = {})
            }
        }
    }
}