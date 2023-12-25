package com.haenari.haenari.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.haenari.haenari.LegacyUtil.isInvalid
import com.haenari.haenari.LegacyUtil.isValid
import com.haenari.haenari.data.database.HaenariDatabase
import com.haenari.haenari.data.entity.WeatherEntity
import com.haenari.haenari.data.entity.containsDate
import com.haenari.haenari.data.entity.getValue

@Dao
interface WeatherDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertWeather(entity: WeatherEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWeatherList(entities: List<WeatherEntity>): List<Long>

    // 있으면 가져와서 비교하고 넣고, 없으면 insert 한다.
    @Transaction
    suspend fun insertOrUpdateWeatherList(
        entities: List<WeatherEntity>,
        startDate: String,
        endDate: String
    ): List<Long> {
        val oldEntities = selectWeather(startDate, endDate)
        val compareValue = entities.map { entity ->
            if (oldEntities.containsDate(entity.date)) {
                oldEntities.getValue(entity.date).run {
                    var result: WeatherEntity = this
                    if (maxTemperature.isInvalid()) {
                        if (entity.maxTemperature.isValid()) {
                            result = result.copy(maxTemperature = entity.maxTemperature)
                        }
                    }

                    if (minTemperature.isInvalid()) {
                        if (entity.minTemperature.isValid()) {
                            result = result.copy(minTemperature = entity.minTemperature)
                        }
                    }

                    if (precipitationTypeAM.isInvalid()) {
                        if (entity.precipitationTypeAM.isValid()) {
                            result = result.copy(precipitationTypeAM = entity.precipitationTypeAM)
                        }
                    }

                    if (precipitationTypePM.isInvalid()) {
                        if (entity.precipitationTypePM.isValid()) {
                            result = result.copy(precipitationTypePM = entity.precipitationTypePM)
                        }
                    }

                    if (probabilityOfPrecipitationAM.isInvalid()) {
                        if (entity.probabilityOfPrecipitationAM.isValid()) {
                            result =
                                result.copy(probabilityOfPrecipitationAM = entity.probabilityOfPrecipitationAM)
                        }
                    }

                    if (probabilityOfPrecipitationPM.isInvalid()) {
                        if (entity.probabilityOfPrecipitationPM.isValid()) {
                            result =
                                result.copy(probabilityOfPrecipitationPM = entity.probabilityOfPrecipitationPM)
                        }
                    }

                    if (skyStatusAM.isInvalid()) {
                        if (entity.skyStatusAM.isValid()) {
                            result = result.copy(skyStatusAM = entity.skyStatusAM)
                        }
                    }

                    if (skyStatusPM.isInvalid()) {
                        if (entity.skyStatusPM.isValid()) {
                            result = result.copy(skyStatusPM = entity.skyStatusPM)
                        }
                    }
                    result
                }
            } else {
                entity
            }
        }

        return insertWeatherList(compareValue)
    }

    @Query("SELECT * FROM ${HaenariDatabase.WEATHER_TABLE}")
    fun selectWeather(): List<WeatherEntity>

    @Query(
        """
        SELECT *
        FROM ${HaenariDatabase.WEATHER_TABLE}
        WHERE date = :date
    """
    )
    fun selectWeather(date: String): WeatherEntity

    // todo DateTime으로 넣자
    @Query(
        """
        SELECT *
        FROM ${HaenariDatabase.WEATHER_TABLE}
        WHERE date BETWEEN :startDate AND :endDate
    """
    )
    fun selectWeather(startDate: String, endDate: String): List<WeatherEntity>

    @Query("DELETE from ${HaenariDatabase.WEATHER_TABLE}")
    fun deleteWeather()
}