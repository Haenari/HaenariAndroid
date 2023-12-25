package com.haenari.haenari

// todo replace function
fun getMidTermLandCoordinate(address: String): String {
    return midTermLandWeatherCoordinate.first {
        address.contains(it.region)
    }.code
}

fun getMidTermTemperatureCoordinate(address: String): String {
    return midTermTemperatureCoordinates.first {
        address.contains(it.region)
    }.code
}