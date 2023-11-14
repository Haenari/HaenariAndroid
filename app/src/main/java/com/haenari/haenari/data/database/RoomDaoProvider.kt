package com.haenari.haenari.data.database

import com.haenari.haenari.data.dao.WeatherDao

interface RoomDaoProvider {

    fun weatherDao(): WeatherDao
}