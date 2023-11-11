package com.haenari.haenari.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.haenari.haenari.data.database.HaenariDatabase

@Entity(
    tableName = HaenariDatabase.WEATHER_TABLE
)
data class WeatherEntity(
    @PrimaryKey val date: String,
    val minTemperature: Int,
    val maxTemperature: Int,
    val skyStatus: String,
    val probabilityOfPrecipitation: Int
)