package com.example.bottomnavigationviewtest.repository

import com.example.bottomnavigationviewtest.models.HomeDataResponse
import com.example.bottomnavigationviewtest.network.RetrofitInstance
import retrofit2.Call

object HomeRepository {
    fun getHomeData(): Call<HomeDataResponse> {
        return RetrofitInstance.api.getHomeData()
    }
}
