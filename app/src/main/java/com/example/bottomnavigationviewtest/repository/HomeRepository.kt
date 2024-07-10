package com.example.bottomnavigationviewtest.repository

import android.content.Context
import android.util.Log
import com.example.bottomnavigationviewtest.models.HomeDataResponse
import com.example.bottomnavigationviewtest.network.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object HomeRepository {
    fun getHomeData(): Call<HomeDataResponse> {
        return RetrofitInstance.api.getHomeData()
    }
}

