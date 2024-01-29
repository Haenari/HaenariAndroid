package com.haenari.haenari.presentation.views.home

import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.haenari.haenari.R
import com.haenari.haenari.databinding.FragmentHomeBinding
import com.haenari.haenari.presentation.base.fragment.MVIFragment
import com.haenari.haenari.presentation.util.Logs
import com.haenari.haenari.presentation.util.Permissions
import com.haenari.haenari.presentation.util.Weathers
import com.haenari.haenari.presentation.views.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import org.joda.time.DateTime

@AndroidEntryPoint
class HomeFragment :
    MVIFragment<FragmentHomeBinding, HomeEvent, HomeState>(R.layout.fragment_home) {
    companion object {
        fun newInstance() = HomeFragment()
    }

    override val viewModel: HomeViewModel by activityViewModels()

    override fun render(state: HomeState) {
        Logs.e("HomeFragment::render::$state")
        bind {
            with(state) {
                tvLocation.text = address
                tvWeather.text = "${
                    Weathers.getTodayString(
                        requireContext(),
                        dailyWeather.precipitationTypeAM
                    )
                }\n${dailyWeather.minTemperature}°C ~ ${dailyWeather.maxTemperature}°C"

                if(dailyWeather.value.isNotEmpty()) {
                    tvCurrentTemperature.text = "${dailyWeather.value[DateTime.now().hourOfDay].temperature}°C"
                }

                context?.let {
                    // todo replace currentWeather
                    if (dailyWeather.value.isNotEmpty()) {
                        val currentWeather = dailyWeather.value.first { weatherForHour ->
                            weatherForHour.precipitationType != Int.MAX_VALUE
                        }.precipitationType

                        Glide.with(it).load(Weathers.getImage(currentWeather)).into(ivWeather)
                    } else {
                        Glide.with(it).load(Weathers.getImage(dailyWeather.precipitationTypeAM))
                            .into(ivWeather)
                    }
                }
            }
        }
    }

    override fun initView() {
        bind {
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