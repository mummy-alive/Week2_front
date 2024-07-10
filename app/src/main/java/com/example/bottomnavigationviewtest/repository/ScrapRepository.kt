package com.example.bottomnavigationviewtest.repository

import com.example.bottomnavigationviewtest.models.recruitpost.RecruitPost
import com.example.bottomnavigationviewtest.network.RetrofitInstance

object ScrapRepository {
    suspend fun getScrapPosts(): List<RecruitPost> {
        return try {
            val response = RetrofitInstance.api.getScrapPosts()
            if (response.isSuccessful) {
                response.body() ?: emptyList()
            } else {
                emptyList()
            }
        } catch (e: Exception) {
            emptyList()
        }
    }
}
