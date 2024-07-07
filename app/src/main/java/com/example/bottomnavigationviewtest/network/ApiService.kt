package com.example.bottomnavigationviewtest.network

import com.example.bottomnavigationviewtest.models.RecruitPost
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("auth/kakao/login/")
    fun sendKakaoToken(@Body token: TokenRequest): Call<LoginResponse>
    fun getRecruitPosts(): Call<List<RecruitPost>>
}

data class TokenRequest(
    val token: String
)

data class LoginResponse(
    val success: Boolean,
    val message: String
)
