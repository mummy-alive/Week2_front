package com.example.bottomnavigationviewtest.network

import com.example.bottomnavigationviewtest.models.HomeDataResponse
import com.example.bottomnavigationviewtest.models.TokenResponse
import com.example.bottomnavigationviewtest.models.profile.Profile
import com.example.bottomnavigationviewtest.models.UserLikeResponse
import com.example.bottomnavigationviewtest.models.profile.ProfileResponse
import com.example.bottomnavigationviewtest.models.User
import com.example.bottomnavigationviewtest.models.UserBlockResponse
import com.example.bottomnavigationviewtest.models.recruitpost.RecruitPost
import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

data class LoginRequest(val name:String, val email: String, val password: String)
data class LoginResponse(val token: String, val user: User)

data class User(val id: Int, val email: String, val name: String)

// GET/POST 메서드
interface ApiService {

    // 카카오 로그인 : 로그인 정보를 넘김
/*    @POST("login/")
    fun login(@Body request: LoginRequest): Call<LoginResponse>*/
    @POST("login/")
    fun loginWithKakao(@Header("Authorization") kakaoToken: String, @Body request: LoginRequest): Call<LoginResponse>

    @POST("api/token/refresh/")
    fun refreshAccessToken(
        @Body refreshToken: Map<String, String>
    ): Call<TokenResponse>

    // 이메일로 프로필 조회
    @GET("api/profile/")
    fun getUserProfile(@Query("email") email: String): Call<Profile>

    @POST("register/")
    fun createUser(
        @Body user: User
    ): Call<Boolean>

    @GET("users/{email}")
    fun getUserByEmail(@Path("email") email: String): Call<User>

    // 프로필 생성
    @POST("api/profile/")
    fun createProfile(
        @Body profile: Profile
    ): Call<ProfileResponse>

    // 사용자의 프로필 저장 목적
    @GET("api/profile/")
    suspend fun getProfile(): Response<Profile>

    // 포스트 조회
    @GET("api/posts/")
    suspend fun getPosts(): Response<List<RecruitPost>>

    // 포스트 업로드
    @POST("api/posts")
    fun uploadPost(@Body post: RecruitPost): Call<RecruitPost>

    // 스크랩 관련
    // 스크랩 상태 확인
    @GET("api/myTab/scraplist/{postId}")
    fun getScrap(@Path("postId") postId: Int): Call<Boolean>

    // 스크랩 추가
    @POST("api/myTab/scraplist/{postId}")
    fun addScrap(@Path("postId") postId: Int): Call<Void>

    // 스크랩 제거
    @DELETE("api/myTab/scraplist/{postId}")
    fun removeScrap(@Path("postId") postId: Int): Call<Void>

    // 스크랩 조회
    @GET("api/myTab/scraplist/")
    suspend fun getScrapPosts(): Response<List<RecruitPost>>


    // Gemini로 필터한 프로필리스트
    @GET("api/profilelist/")
    fun getAutoMatchings(@Query("email") email: String): Call<List<Profile>>

    // 최근 네 개 포스트 조회하기
    // api/main에서 (게시글 리스트/프로필 리스트)
    @GET("api/main/")
    fun getHomeData(): Call<HomeDataResponse>
    // Bearer $token




    // 포스트 삭제
    @DELETE("api/posts/{id}")
    fun deletePost(@Path("id") id: Int) : Call<Void>
    // 포스트 편집
    @PUT("api/posts/{id}")
    fun editPost(@Path("id") id: Int, @Body post: RecruitPost) : Call<RecruitPost>

    @GET("email/")
    fun getUserEmail(@Header("Authorization") authToken: String): Call<EmailResponse>

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

data class EmailResponse(val email: String)