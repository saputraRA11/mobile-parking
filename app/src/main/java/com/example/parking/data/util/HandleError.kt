package com.example.parking.data.util

import org.json.JSONObject
import retrofit2.Response

fun <T> retrofitErrorHandler(res: Response<T>): T {
    if (res.isSuccessful) {
        return res.body()!!
    } else {
        val errMsg = res.errorBody()?.string()?.let {
            JSONObject(it).getString("message")
        } ?: run {
            res.code().toString()
        }

        throw Exception(errMsg)
    }
}