package com.haenari.haenari.data.api

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitCreator: RetrofitProvider {

    companion object {
        val instance: RetrofitProvider
            get() = Factory.INSTANCE

        private object Factory {
            val INSTANCE = RetrofitCreator()
        }
    }

    private val gson = GsonBuilder()
        .setLenient()
        .create()

    private val builder = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create(gson))

    private val client = OkHttpClient.Builder()
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .build()

    override fun build(baseUrl: String): Retrofit {
        builder.baseUrl(baseUrl)
        builder.client(client)
        return builder.build()
    }

    private val sample by lazy { build("sample url") }

    override fun sampleAPI(): SampleAPI = sample.create(SampleAPI::class.java)
}