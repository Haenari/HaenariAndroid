package com.haenari.haenari.data.api

import retrofit2.http.GET

interface SampleAPI {

    @GET
    fun test()
}