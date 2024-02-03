package com.haenari.haenari.presentation.views.weather

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.haenari.haenari.R
import com.haenari.haenari.data.entity.WeatherEntity
import com.haenari.haenari.databinding.ItemWeatherBinding
import com.haenari.haenari.presentation.base.BaseRecyclerAdapter
import com.haenari.haenari.presentation.base.BaseRecyclerViewHolder
import com.haenari.haenari.presentation.util.DateTimes
import com.haenari.haenari.presentation.util.Weathers
import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat

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
                val today = DateTime.now().toString("yyyyMMdd")
                val date = DateTime.parse(item.date, DateTimeFormat.forPattern("yyyyMMdd"))

                if (today == item.date) {
                    tvTitle.text = itemView.context.getString(R.string.today_kr)
                    tvMinTemperature.setTextColor(ContextCompat.getColor(itemView.context, R.color.c_3491FF))
                    tvMaxTemperature.setTextColor(ContextCompat.getColor(itemView.context, R.color.c_FF6465))
                    clRoot.background = ContextCompat.getDrawable(itemView.context, R.drawable.shape_rect12_3fffffff_)
                } else {
                    tvTitle.text = DateTimes.getDayOfWeekKrStr(date.dayOfWeek)
                    tvMinTemperature.setTextColor(ContextCompat.getColor(itemView.context, R.color.white))
                    tvMaxTemperature.setTextColor(ContextCompat.getColor(itemView.context, R.color.white))
                    clRoot.background = ContextCompat.getDrawable(itemView.context, R.drawable.shape_rect12_19000000_)
                }

                tvMinTemperature.text = "${item.minTemperature.toInt()}°"
                tvMaxTemperature.text = "${item.maxTemperature.toInt()}°"

                Glide.with(itemView).load(Weathers.getImage(item.precipitationTypeAM))
                    .into(ivAmWeather)
                Glide.with(itemView).load(Weathers.getImage(item.precipitationTypePM))
                    .into(ivPmWeather)
            }
        }
    }
}