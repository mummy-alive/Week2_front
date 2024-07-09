package com.example.bottomnavigationviewtest.repository

import com.example.bottomnavigationviewtest.models.recruitpost.RecruitPost
import com.example.bottomnavigationviewtest.network.RetrofitInstance

object PostRepository {
    suspend fun getPosts(): List<RecruitPost>? {
        return try {
            val response = RetrofitInstance.api.getPosts()
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
