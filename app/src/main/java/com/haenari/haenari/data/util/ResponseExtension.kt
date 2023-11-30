package com.haenari.haenari.data.util

import retrofit2.Response

fun <T> Response<T>.checkIsSuccess(onSuccess: (T) -> Unit): Boolean {
    try {
        return if (this.isSuccessful) {
            this.body()?.let { entity ->
                onSuccess(entity)
                true
            } ?: false
        } else {
            false
        }
    } catch (e: Exception) {
        // todo custom exception
        this.errorBody()
        throw e
    }
}