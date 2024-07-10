package com.example.bottomnavigationviewtest.repository

import com.example.bottomnavigationviewtest.models.profile.Profile
import com.example.bottomnavigationviewtest.network.RetrofitInstance
import retrofit2.Call

object MatchingRepository {
    fun getAutoMatchings(email: String): Call<List<Profile>> {
        return RetrofitInstance.api.getAutoMatchings(email)
    }
}
