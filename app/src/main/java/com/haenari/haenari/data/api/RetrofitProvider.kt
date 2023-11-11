package com.haenari.haenari.data.api

import retrofit2.Retrofit

interface RetrofitProvider {

    fun build(baseUrl: String): Retrofit

    fun weatherAPI(): WeatherAPI
}