package com.haenari.haenari.data.dao

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.haenari.haenari.data.entity.Weather

class WeatherTypeConverter {

    private val gson = Gson()

    @TypeConverter
    fun stringToInt(text: String): Int {
        return text.toInt()
    }

    @TypeConverter
    fun intToString(num: Int): String {
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