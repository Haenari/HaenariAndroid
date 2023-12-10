package com.haenari.haenari.data.util

import retrofit2.Response

suspend fun <T, R> Response<T>.checkIsSuccess(onSuccess: suspend (T) -> R): Boolean {
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