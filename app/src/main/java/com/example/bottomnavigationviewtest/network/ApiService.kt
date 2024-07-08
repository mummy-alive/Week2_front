package com.example.bottomnavigationviewtest.network

import com.example.bottomnavigationviewtest.models.RecruitPost
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @POST("auth/kakao/login/")
    fun sendKakaoToken(@Body token: TokenRequest): Call<LoginResponse>

    // 포스트 조회
    @GET("api/posts")
    fun getPosts(): Call<List<RecruitPost>>

    // 포스트 업로드
    @POST("api/posts")
    fun uploadPost(@Body post: RecruitPost): Call<RecruitPost>
}

data class TokenRequest(
    val token: String
)

data class LoginResponse(
    val success: Boolean,
    val message: String
)
