package com.example.bottomnavigationviewtest.network

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("auth/kakao/login/")
    fun sendKakaoToken(@Body token: TokenRequest): Call<LoginResponse>
}

data class TokenRequest(
    val token: String
)

data class LoginResponse(
    val success: Boolean,
    val message: String
)
