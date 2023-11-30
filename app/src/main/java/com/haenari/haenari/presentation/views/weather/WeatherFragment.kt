package com.haenari.haenari.presentation.views.weather

import com.haenari.haenari.R
import com.haenari.haenari.databinding.FragmentWeatherBinding
import com.haenari.haenari.presentation.base.fragment.BindFragment

class WeatherFragment: BindFragment<FragmentWeatherBinding>(R.layout.fragment_weather){

    companion object {
        fun newInstance() = WeatherFragment()
    }
    override fun initView() {

    }
}