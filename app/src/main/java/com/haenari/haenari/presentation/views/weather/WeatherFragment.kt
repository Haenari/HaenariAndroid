package com.haenari.haenari.presentation.views.weather

import android.graphics.Rect
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import com.haenari.haenari.R
import com.haenari.haenari.databinding.FragmentWeatherBinding
import com.haenari.haenari.presentation.base.fragment.MVIFragment
import com.haenari.haenari.presentation.util.Dimensions
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
        Logs.e("WeatherFragment::render::weatherList::${state.weeklyWeather}")
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
            rvWeather.addItemDecoration(object : ItemDecoration(){
                override fun getItemOffsets(
                    outRect: Rect,
                    view: View,
                    parent: RecyclerView,
                    state: RecyclerView.State
                ) {
                    val startIndex = 0
                    val endIndex = recyclerAdapter.itemCount.minus(1)
                    context?.let {
                        when (parent.getChildAdapterPosition(view)) {
                            startIndex -> {
                                outRect.top = Dimensions.dpToPx(it, 8)
                                outRect.bottom = Dimensions.dpToPx(it, 4)
                            }

                            endIndex -> {
                                outRect.top = Dimensions.dpToPx(it, 4)
                                outRect.bottom = Dimensions.dpToPx(it, 4)
                            }

                            else -> {
                                outRect.top = Dimensions.dpToPx(it, 4)
                                outRect.bottom = Dimensions.dpToPx(it, 8)
                            }
                        }
                    }


                }
            })

            ibCurrentLocation.setOnClickListener {
                Permissions.requestLocationPermission(
                    onPermissionGranted = {
                        (activity as MainActivity).requestLocation()
                    },
                    onPermissionDenied = {})
            }
        }
    }
}