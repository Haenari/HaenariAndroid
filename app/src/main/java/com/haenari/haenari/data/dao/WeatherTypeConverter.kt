package com.haenari.haenari.data.dao

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.haenari.haenari.data.entity.Weather

class WeatherTypeConverter {

    private val gson = Gson()

    @TypeConverter
    fun stringToLong(text: String): Long {
        return text.toLong()
    }

    @TypeConverter
    fun longToString(num: Long): String {
        return num.toString()
    }

    @TypeConverter
    fun listValueToJson(values: List<Weather>): String {
        return gson.toJson(values)
    }

    @TypeConverter
    fun jsonToListValue(json: String): List<Weather> {
        return gson.fromJson(json, Array<Weather>::class.java).toList()
    }
}