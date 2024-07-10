package com.example.bottomnavigationviewtest.repository

import android.content.Context
import android.util.Log
import com.example.bottomnavigationviewtest.models.HomeDataResponse
import com.example.bottomnavigationviewtest.network.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object HomeRepository {
    fun getHomeData(context: Context, callback: Callback<HomeDataResponse>) {
        val token = MyPreferences.getToken(context)
        if (token != null) {
            val authorizationHeader = "Bearer $token"
            Log.d("HomeRepository", "Authorization Header: $authorizationHeader")
            RetrofitInstance.api.getHomeData(authorizationHeader).enqueue(object : Callback<HomeDataResponse> {
                override fun onResponse(call: Call<HomeDataResponse>, response: Response<HomeDataResponse>) {
                    if (response.isSuccessful) {
                        callback.onResponse(call, response)
                    } else {
                        Log.e("HomeRepository", "Error: ${response.code()} - ${response.message()}")
                        callback.onFailure(call, Throwable(response.message()))
                    }
                }

                override fun onFailure(call: Call<HomeDataResponse>, t: Throwable) {
                    Log.e("HomeRepository", "Request failed", t)
                    callback.onFailure(call, t)
                }
            })
        } else {
            callback.onFailure(null, Throwable("Token not found"))
        }
    }
}

