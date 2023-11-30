package com.haenari.haenari.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.haenari.haenari.data.database.HaenariDatabase
import com.haenari.haenari.data.entity.WeatherEntity

@Dao
interface WeatherDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertWeather(entity: WeatherEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertWeatherList(entities: List<WeatherEntity>)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateWeather(entity: WeatherEntity)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateWeatherList(entities: List<WeatherEntity>)

    @Query("SELECT * FROM ${HaenariDatabase.WEATHER_TABLE}")
    fun selectWeather(): List<WeatherEntity>

    @Query("""
        SELECT *
        FROM ${HaenariDatabase.WEATHER_TABLE}
        WHERE date = :date
    """)
    fun selectWeather(date: String): WeatherEntity

    @Query("""
        SELECT *
        FROM ${HaenariDatabase.WEATHER_TABLE}
        WHERE date BETWEEN :startDate AND :endDate
    """)
    fun selectWeather(startDate: Long, endDate: Long): List<WeatherEntity>

    @Query("DELETE from ${HaenariDatabase.WEATHER_TABLE}")
    fun deleteWeather()
}