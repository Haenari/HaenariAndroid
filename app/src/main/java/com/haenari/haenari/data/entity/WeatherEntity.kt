package com.haenari.haenari.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.haenari.haenari.data.dao.WeatherTypeConverter
import com.haenari.haenari.data.database.HaenariDatabase

@Entity(tableName = HaenariDatabase.WEATHER_TABLE)
@TypeConverters(WeatherTypeConverter::class)
data class WeatherEntity(
    @PrimaryKey val date: String = "",
    @ColumnInfo val minTemperature: Float = Float.MAX_VALUE,
    @ColumnInfo val maxTemperature: Float = Float.MAX_VALUE,
    @ColumnInfo val precipitationTypeAM: Int = Int.MAX_VALUE,
    @ColumnInfo val precipitationTypePM: Int = Int.MAX_VALUE,
    @ColumnInfo val probabilityOfPrecipitationAM: Int = Int.MAX_VALUE,
    @ColumnInfo val probabilityOfPrecipitationPM: Int = Int.MAX_VALUE,
    @ColumnInfo val skyStatusAM: Int = Int.MAX_VALUE,
    @ColumnInfo val skyStatusPM: Int = Int.MAX_VALUE,
    @ColumnInfo val value: List<Weather> = emptyList(),
)
fun List<WeatherEntity>.containsDate(date: String): Boolean {
    return this.any { it.date == date }
}

fun List<WeatherEntity>.getValue(date: String): WeatherEntity {
    return this.filter { it.date == date }[0]
}

fun WeatherEntity.toText(): String {
    return StringBuilder().apply {
        append("result\n")
        append("date::$date\n")
        append("minTemperature::$minTemperature\n")
        append("maxTemperature::$maxTemperature\n")
        append("precipitationTypeAM::$precipitationTypeAM\n")
        append("precipitationTypePM::$precipitationTypePM\n")
        append("probabilityOfPrecipitationAM::$probabilityOfPrecipitationAM\n")
        append("probabilityOfPrecipitationPM::$probabilityOfPrecipitationPM\n")
        append("skyStatusAM::$skyStatusAM\n")
        append("skyStatusPM::$skyStatusPM\n")
    }.toString()
}

data class Weather(
    val temperature: Float,
    val skyStatus: Int,
    val probabilityOfPrecipitation: Int,
    val precipitationType: Int
) {
    companion object {
        fun defaultValue() = Weather(
            Float.MAX_VALUE,
            Int.MAX_VALUE,
            Int.MAX_VALUE,
            Int.MAX_VALUE
        )
    }
}