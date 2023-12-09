package com.haenari.haenari.data.repository.weather

import com.haenari.haenari.data.entity.Weather
import com.haenari.haenari.data.entity.WeatherEntity
import com.haenari.haenari.data.model.weather.ShortTermItem

object WeatherMapper {

    fun List<ShortTermItem>.toWeatherEntity(): List<WeatherEntity> {
        return this.groupBy {
            it.fcstDate
        }.map {
            val date = it.key
            val list = it.value.groupBy { it.fcstTime }

            date to list
        }.map { firstMap ->
            val date = checkNotNull(firstMap.first)
            val mutableList = MutableList(24) { Weather.defaultValue() }

            firstMap.second.map { secondMap ->
                val time = secondMap.key
                val index = checkNotNull(time).toInt() / 100
                val list = secondMap.value

                var temperature = Float.MAX_VALUE
                var skyStatus = ""
                var probabilityOfPrecipitation = Int.MAX_VALUE
                list.map {
                    when (it.category) {
                        "VVV" -> {
                            temperature = it.fcstValue?.toFloat() ?: Float.MAX_VALUE
                        }

                        "REH" -> {
                            probabilityOfPrecipitation = it.fcstValue?.toInt() ?: Int.MAX_VALUE
                        }

                        "PTY" -> {
                            skyStatus = it.fcstValue ?: ""
                        }
                    }
                }
                mutableList[index] = Weather(temperature, skyStatus, probabilityOfPrecipitation)
            }
            //Logs.e("result::${WeatherEntity(date, mutableList)}")
            WeatherEntity(date = date, value = mutableList)
        }
    }
}