package com.example.bottomnavigationviewtest.repository

import com.example.bottomnavigationviewtest.models.profile.Profile
import com.example.bottomnavigationviewtest.network.RetrofitInstance
import retrofit2.Call

object ProfileRepository {
    suspend fun getProfile(): Profile? {
        return try {
            val response = RetrofitInstance.api.getProfile()
            if (response.isSuccessful) {
                response.body()
            } else {
                null
            }
        } catch (e: Exception) {
            null
        }
    }

    fun getProfileByEmail(email: String): Call<Profile> {
        return RetrofitInstance.api.getUserProfile(email)
    }
}
