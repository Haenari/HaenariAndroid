package com.haenari.haenari.presentation.views.home

import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.haenari.haenari.R
import com.haenari.haenari.databinding.FragmentHomeBinding
import com.haenari.haenari.presentation.base.fragment.MVIFragment
import com.haenari.haenari.presentation.util.Logs
import com.haenari.haenari.presentation.util.Weathers
import dagger.hilt.android.AndroidEntryPoint

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

                context?.let {
                    Glide.with(it).load(Weathers.getImage(dailyWeather.precipitationTypeAM)).into(ivWeather)
                }
            }
        }
    }

    override fun initView() {

    }
}