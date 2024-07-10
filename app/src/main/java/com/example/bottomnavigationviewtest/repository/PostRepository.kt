package com.example.bottomnavigationviewtest.repository

import com.example.bottomnavigationviewtest.models.recruitpost.RecruitPost
import com.example.bottomnavigationviewtest.network.RetrofitInstance
import retrofit2.Response

object PostRepository {
    suspend fun getPosts(): Response<List<RecruitPost>> {
        return RetrofitInstance.api.getPosts()
    }
}
