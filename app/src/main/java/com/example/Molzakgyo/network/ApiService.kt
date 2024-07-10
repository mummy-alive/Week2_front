package com.example.Molzakgyo.network

import com.example.Molzakgyo.models.MatchingProfile
import com.example.Molzakgyo.models.RecruitPost
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

// GET/POST 메서드
interface ApiService {
    // 카카오 로그인 : 로그인 정보를 넘김
    @POST("auth/kakao/login/")
    fun sendKakaoToken(@Body token: TokenRequest): Call<LoginResponse>

    // 포스트 조회
    @GET("api/posts")
    fun getPosts(): Call<List<RecruitPost>>

    // 포스트 업로드
    @POST("api/posts")
    fun uploadPost(@Body post: RecruitPost): Call<RecruitPost>

    // 포스트 삭제
    @DELETE("api/posts/{id}")
    fun deletePost(@Path("id") id: Int) : Call<Void>
    // 포스트 편집
    @PUT("api/posts/{id}")
    fun editPost(@Path("id") id: Int, @Body post: RecruitPost) : Call<RecruitPost>

    // 수동매칭-프로필 가져오기

    @GET("profiles")
    suspend fun getProfiles(): List<MatchingProfile>
}

data class TokenRequest(
    val token: String
)


data class LoginResponse(
    val success: Boolean,
    val message: String,
    val userId: String?
)

data class User(
    val id: String,
    val name: String,
    // 기타 사용자 정보
)