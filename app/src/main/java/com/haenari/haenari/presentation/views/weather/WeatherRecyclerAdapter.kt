package com.haenari.haenari.presentation.views.weather

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.haenari.haenari.R
import com.haenari.haenari.data.entity.WeatherEntity
import com.haenari.haenari.databinding.ItemWeatherBinding
import com.haenari.haenari.presentation.base.BaseRecyclerAdapter
import com.haenari.haenari.presentation.base.BaseRecyclerViewHolder
import com.haenari.haenari.presentation.util.DateTimes
import com.haenari.haenari.presentation.util.Weathers
import org.joda.time.DateTime

class WeatherRecyclerAdapter :
    BaseRecyclerAdapter<WeatherEntity, ItemWeatherBinding>(R.layout.item_weather) {
    override fun createHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseRecyclerViewHolder<WeatherEntity, ItemWeatherBinding> {
        return WeatherDetailsRecyclerViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_weather, parent, false)
        )
    }

    inner class WeatherDetailsRecyclerViewHolder(itemView: View) :
        BaseRecyclerViewHolder<WeatherEntity, ItemWeatherBinding>(itemView) {
        override fun bindData(item: WeatherEntity) {
            binding?.run {
                val date = DateTime.now().plusDays(layoutPosition + 1)
                tvTitle.text =
                    "${date.toString("MM월 dd일")} ${DateTimes.getDayOfWeekKrStr(date.dayOfWeek)}"
                tvLandWeather.text = "${Weathers.getString(itemView.context, item.precipitationTypeAM)} // ${Weathers.getString(itemView.context, item.precipitationTypePM)}"
                tvTemperature.text = "${item.minTemperature}°C ~ ${item.maxTemperature}°C"
            }
        }
    }
}