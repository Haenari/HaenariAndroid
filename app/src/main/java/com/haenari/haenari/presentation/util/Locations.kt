package com.haenari.haenari.presentation.util

import android.location.Address
import kotlin.math.cos
import kotlin.math.ln
import kotlin.math.pow
import kotlin.math.sin
import kotlin.math.tan

object Locations {
    const val DEFAULT_LATITUDE = 37.3957122
    const val DEFAULT_LONGITUDE = 127.1105181

    fun toCoordinates(lat: Double, lng: Double): Pair<Int, Int> {
        val RE = 6371.00877     // 지구 반경(km)
        val GRID = 5.0          // 격자 간격(km)
        val SLAT1 = 30.0        // 투영 위도1(degree)
        val SLAT2 = 60.0        // 투영 위도2(degree)
        val OLON = 126.0        // 기준점 경도(degree)
        val OLAT = 38.0         // 기준점 위도(degree)
        val XO = 43             // 기준점 X좌표(GRID)
        val YO = 136            // 기준점 Y좌표(GRID)
        val DEGRAD = Math.PI / 180.0
        val re = RE / GRID
        val slat1 = SLAT1 * DEGRAD
        val slat2 = SLAT2 * DEGRAD
        val olon = OLON * DEGRAD
        val olat = OLAT * DEGRAD

        var sn = tan(Math.PI * 0.25 + slat2 * 0.5) / tan(Math.PI * 0.25 + slat1 * 0.5)
        sn = ln(cos(slat1) / cos(slat2)) / ln(sn)
        var sf = tan(Math.PI * 0.25 + slat1 * 0.5)
        sf = sf.pow(sn) * cos(slat1) / sn
        var ro = tan(Math.PI * 0.25 + olat * 0.5)
        ro = re * sf / ro.pow(sn)

        var ra = tan(Math.PI * 0.25 + (lat) * DEGRAD * 0.5)
        ra = re * sf / ra.pow(sn)
        var theta = lng * DEGRAD - olon
        if (theta > Math.PI) theta -= 2.0 * Math.PI
        if (theta < -Math.PI) theta += 2.0 * Math.PI
        theta *= sn

        val x = (ra * sin(theta) + XO + 0.5).toInt()
        val y = (ro - ra * cos(theta) + YO + 0.5).toInt()

        return (x to y)
    }

    fun getAddress(address: Address): String {
        val splitString = address.getAddressLine(0).split(" ")
        return "${splitString[1]} ${splitString[2]}"
    }
}