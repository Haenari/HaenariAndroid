package com.haenari.haenari.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.haenari.haenari.data.dao.WeatherDao
import com.haenari.haenari.data.entity.WeatherEntity

@Database(
    version = 1,
    exportSchema = false,
    entities = [WeatherEntity::class]
)
abstract class HaenariDatabase : RoomDatabase() {
    companion object {
        const val WEATHER_TABLE: String = "weather_table"
    }

    abstract fun weatherDao(): WeatherDao
}