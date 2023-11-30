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
    @PrimaryKey val date: String,
    @ColumnInfo val value: List<Weather>
)

data class Weather(
    val temperature: Float,
    val skyStatus: String,
    val probabilityOfPrecipitation: Int
) {
    companion object {
        fun defaultValue() = Weather(
            Float.MAX_VALUE,
            "",
            Int.MAX_VALUE
        )
    }
}