package com.example.bottomnavigationviewtest.repository

import com.example.bottomnavigationviewtest.models.profile.Profile
import com.example.bottomnavigationviewtest.network.RetrofitInstance

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
}
