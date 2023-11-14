package com.haenari.haenari.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.haenari.haenari.data.entity.WeatherEntity

@Database(
    version = 1,
    exportSchema = false,
    entities = [WeatherEntity::class]
)
abstract class HaenariDatabase : RoomDatabase(), RoomDaoProvider {
    companion object {
        private const val DATABASE_NAME = "haenari.db"
        const val WEATHER_TABLE: String = "weather_table"

        fun create(context: Context): RoomDaoProvider =
            Room.databaseBuilder(context, HaenariDatabase::class.java, DATABASE_NAME)
                .build()
    }
}