package com.example.bottomnavigationviewtest.network

import com.example.bottomnavigationviewtest.models.UserLikeResponse
import com.example.bottomnavigationviewtest.models.Profile
import com.example.bottomnavigationviewtest.models.RecruitPost
import com.example.bottomnavigationviewtest.models.UserBlockResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

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
    @GET("api/profile")
    fun getProfiles() : Call<List<Profile>>

    @GET("profile")
    suspend fun _getProfiles(): List<Profile>

    @GET("api/profile")
    fun getProfileByEmail(@Query("email") email: String): Call<Profile>

    // api/myTab/likelist/
    // api/myTab/blocklist/
    // api/token/refresh


    @GET("api/likes/")
    fun getLikedProfiles(@Header("Authorization") token: String): Call<List<UserLikeResponse>>

    @GET("api/blocks/")
    fun getBlockedProfiles(@Header("Authorization") token: String): Call<List<UserBlockResponse>>

    // 싫어요
    @GET("api/liked_profiles/{from_id}/")
    fun getBlockedProfiles(@Path("from_id") fromId: Int): Call<List<UserBlockResponse>>

    @DELETE("api/likes/{like_id}/")
    fun deleteBlock(@Path("like_id") likeId: Int): Call<Void>
    // 스크랩/내 글도 똑같이 구현


    // 차단목록 조회
}

data class TokenRequest(
    val token: String
)

data class LoginRequest(
    val email: String,
    val password: String
)

data class LoginResponse(
    val success: Boolean,
    val message: String,
    val userId: String?
)