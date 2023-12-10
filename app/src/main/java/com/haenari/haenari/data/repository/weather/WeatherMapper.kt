package com.haenari.haenari.data.repository.weather

import com.haenari.haenari.LegacyUtil.handleAmPm
import com.haenari.haenari.data.entity.Weather
import com.haenari.haenari.data.entity.WeatherEntity
import com.haenari.haenari.data.model.weather.ShortTermItem
import java.lang.Integer.max

object WeatherMapper {

    private const val MAX_TEMPERATURE = "TMX"
    private const val MIN_TEMPERATURE = "TMN"
    private const val PRECIPITATION_TYPE = "PTY"
    private const val PROBABILITY_OF_PRECIPITATION = "POP"
    private const val SKY_STATUS = "SKY"
    private const val TEMPERATURE = "TMP"

    fun List<ShortTermItem>.toWeatherEntity(): List<WeatherEntity> {
        return this.groupBy {
            it.fcstDate
        }.map {
            val date = it.key
            val list = it.value.groupBy { it.fcstTime }

            date to list
        }.map { dateMap ->
            val date = checkNotNull(dateMap.first)
            val mutableList = MutableList(24) { Weather.defaultValue() }
            var maxTemperature = Float.MAX_VALUE
            var minTemperature = Float.MAX_VALUE
            var skyStatusAM = Int.MAX_VALUE
            var skyStatusPM = Int.MAX_VALUE
            var precipitationTypeAM = Int.MAX_VALUE
            var precipitationTypePM = Int.MAX_VALUE
            var probabilityOfPrecipitationAM = Int.MIN_VALUE
            var probabilityOfPrecipitationPM = Int.MIN_VALUE

            dateMap.second.map { (time, list) ->
                val hour = checkNotNull(time).toInt().div(100)

                var temperature = Float.MAX_VALUE
                var skyStatus = Int.MAX_VALUE
                var precipitationType = Int.MAX_VALUE
                var probabilityOfPrecipitation = Int.MAX_VALUE
                list.map {
                    when (it.category) {
                        TEMPERATURE -> {
                            temperature = it.fcstValue?.toFloat() ?: Float.MAX_VALUE
                        }

                        PROBABILITY_OF_PRECIPITATION -> {
                            probabilityOfPrecipitation = it.fcstValue?.toInt() ?: Int.MAX_VALUE

                            hour.handleAmPm(
                                onAM = {
                                    probabilityOfPrecipitationAM = max(
                                        probabilityOfPrecipitationAM,
                                        probabilityOfPrecipitation
                                    )
                                },
                                onPM = {
                                    probabilityOfPrecipitationPM = max(
                                        probabilityOfPrecipitationPM,
                                        probabilityOfPrecipitation
                                    )
                                }
                            )
                        }

                        PRECIPITATION_TYPE -> {
                            precipitationType = it.fcstValue?.toInt() ?: Int.MAX_VALUE

                            hour.handleAmPm(
                                onAM = {
                                    precipitationTypeAM = checkPrecipitationTypePriority(
                                        precipitationTypeAM,
                                        precipitationType
                                    )
                                },
                                onPM = {
                                    precipitationTypePM = checkPrecipitationTypePriority(
                                        precipitationTypePM,
                                        precipitationType
                                    )
                                }
                            )
                        }

                        SKY_STATUS -> {
                            skyStatus = it.fcstValue?.toInt() ?: Int.MAX_VALUE

                            hour.handleAmPm(
                                onAM = {
                                    skyStatusAM = checkSkyStatusPriority(skyStatusAM, skyStatus)
                                },
                                onPM = {
                                    skyStatusPM = checkSkyStatusPriority(skyStatusPM, skyStatus)
                                }
                            )
                        }

                        MAX_TEMPERATURE -> {
                            maxTemperature = it.fcstValue?.toFloat() ?: Float.MAX_VALUE
                        }

                        MIN_TEMPERATURE -> {
                            minTemperature = it.fcstValue?.toFloat() ?: Float.MAX_VALUE
                        }
                    }
                }

                mutableList[hour] = Weather(
                    temperature = temperature,
                    skyStatus = skyStatus,
                    probabilityOfPrecipitation = probabilityOfPrecipitation,
                    precipitationType = precipitationType
                )
            }
            WeatherEntity(
                date = date,
                minTemperature = minTemperature,
                maxTemperature = maxTemperature,
                precipitationTypeAM = precipitationTypeAM,
                precipitationTypePM = precipitationTypePM,
                probabilityOfPrecipitationAM = probabilityOfPrecipitationAM,
                probabilityOfPrecipitationPM = probabilityOfPrecipitationPM,
                skyStatusAM = skyStatusAM,
                skyStatusPM = skyStatusPM,
                value = mutableList,
            )
        }
    }

    private fun checkPrecipitationTypePriority(oldValue: Int, newValue: Int): Int {
        // todo check priority
        return newValue
    }

    private fun checkSkyStatusPriority(oldValue: Int, newValue: Int): Int {
        // todo check priority
        return newValue
    }
}