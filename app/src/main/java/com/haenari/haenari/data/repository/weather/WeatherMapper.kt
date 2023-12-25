package com.haenari.haenari.data.repository.weather

import com.haenari.haenari.LegacyUtil.handleAmPm
import com.haenari.haenari.data.entity.Weather
import com.haenari.haenari.data.entity.WeatherEntity
import com.haenari.haenari.data.model.weather.MidTermLandItem
import com.haenari.haenari.data.model.weather.MidTermTemperatureItem
import com.haenari.haenari.data.model.weather.ShortTermItem
import com.haenari.haenari.presentation.util.DateTimes.date
import org.joda.time.DateTime
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

    fun MidTermLandItem.toWeatherEntity(): List<WeatherEntity> {
        val date = DateTime.now()
        return listOf(
            WeatherEntity(
                date = date.plusDays(3).date(),
                probabilityOfPrecipitationAM = rnSt3Am ?: Int.MAX_VALUE,
                probabilityOfPrecipitationPM = rnSt3Pm ?: Int.MAX_VALUE,
                skyStatusAM = getSkyStatus(wf3Am ?: ""),
                skyStatusPM = getSkyStatus(wf3Pm ?: ""),
                precipitationTypeAM = getPrecipitationType(wf3Am ?: ""),
                precipitationTypePM = getPrecipitationType(wf3Pm ?: "")
            ),
            WeatherEntity(
                date = date.plusDays(4).date(),
                probabilityOfPrecipitationAM = rnSt4Am ?: Int.MAX_VALUE,
                probabilityOfPrecipitationPM = rnSt4Pm ?: Int.MAX_VALUE,
                skyStatusAM = getSkyStatus(wf4Am ?: ""),
                skyStatusPM = getSkyStatus(wf4Pm ?: ""),
                precipitationTypeAM = getPrecipitationType(wf4Am ?: ""),
                precipitationTypePM = getPrecipitationType(wf4Pm ?: "")
            ),
            WeatherEntity(
                date = date.plusDays(5).date(),
                probabilityOfPrecipitationAM = rnSt5Am ?: Int.MAX_VALUE,
                probabilityOfPrecipitationPM = rnSt5Pm ?: Int.MAX_VALUE,
                skyStatusAM = getSkyStatus(wf5Am ?: ""),
                skyStatusPM = getSkyStatus(wf5Pm ?: ""),
                precipitationTypeAM = getPrecipitationType(wf5Am ?: ""),
                precipitationTypePM = getPrecipitationType(wf5Pm ?: "")
            ),
            WeatherEntity(
                date = date.plusDays(6).date(),
                probabilityOfPrecipitationAM = rnSt6Am ?: Int.MAX_VALUE,
                probabilityOfPrecipitationPM = rnSt6Pm ?: Int.MAX_VALUE,
                skyStatusAM = getSkyStatus(wf6Am ?: ""),
                skyStatusPM = getSkyStatus(wf6Pm ?: ""),
                precipitationTypeAM = getPrecipitationType(wf6Am ?: ""),
                precipitationTypePM = getPrecipitationType(wf6Pm ?: "")
            ),
            WeatherEntity(
                date = date.plusDays(7).date(),
                probabilityOfPrecipitationAM = rnSt7Am ?: Int.MAX_VALUE,
                probabilityOfPrecipitationPM = rnSt7Pm ?: Int.MAX_VALUE,
                skyStatusAM = getSkyStatus(wf7Am ?: ""),
                skyStatusPM = getSkyStatus(wf7Pm ?: ""),
                precipitationTypeAM = getPrecipitationType(wf7Am ?: ""),
                precipitationTypePM = getPrecipitationType(wf7Pm ?: "")
            ),
        )
    }

    fun MidTermTemperatureItem.toWeatherEntity(): List<WeatherEntity> {
        val date = DateTime.now()
        return listOf(
            WeatherEntity(
                date = date.plusDays(3).date(),
                minTemperature = taMin3 ?: Float.MAX_VALUE,
                maxTemperature = taMax3 ?: Float.MAX_VALUE
            ),
            WeatherEntity(
                date = date.plusDays(4).date(),
                minTemperature = taMin4 ?: Float.MAX_VALUE,
                maxTemperature = taMax4 ?: Float.MAX_VALUE
            ),
            WeatherEntity(
                date = date.plusDays(5).date(),
                minTemperature = taMin5 ?: Float.MAX_VALUE,
                maxTemperature = taMax5 ?: Float.MAX_VALUE
            ),
            WeatherEntity(
                date = date.plusDays(6).date(),
                minTemperature = taMin6 ?: Float.MAX_VALUE,
                maxTemperature = taMax6 ?: Float.MAX_VALUE
            ),
            WeatherEntity(
                date = date.plusDays(7).date(),
                minTemperature = taMin7 ?: Float.MAX_VALUE,
                maxTemperature = taMax7 ?: Float.MAX_VALUE
            ),
        )
    }

    private fun checkPrecipitationTypePriority(oldValue: Int, newValue: Int): Int {
        // todo check priority
        return newValue
    }

    private fun checkSkyStatusPriority(oldValue: Int, newValue: Int): Int {
        // todo check priority
        return newValue
    }

    private fun getSkyStatus(weather: String): Int {
        return if (weather.contains("구름")) 3
        else if (weather.contains("흐림") || weather.contains("흐리고")) 4
        else 1
    }

    private fun getPrecipitationType(weather: String): Int {
        return if (weather.contains("비/눈")) 2
        else if (weather.contains("비")) 1
        else if (weather.contains("눈")) 3
        else if (weather.contains("소나기")) 4
        else 0
    }
}