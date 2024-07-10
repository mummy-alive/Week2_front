package com.example.bottomnavigationviewtest.network

import android.content.Context
import okhttp3.Interceptor
import okhttp3.Response
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AuthInterceptor(private val context: Context) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        val token = MyPreferences.getToken(context) ?: ""

        // Add Authorization header with the access token
        request = request.newBuilder()
            .addHeader("Authorization", "Bearer $token")
            .build()

        val response = chain.proceed(request)

        // If token is expired, refresh it
        if (response.code == 401) {
            synchronized(this) {
                val newToken = refreshAccessToken()
                if (newToken != null) {
                    MyPreferences.saveToken(context, newToken)
                    // Retry the request with the new token
                    request = request.newBuilder()
                        .removeHeader("Authorization")
                        .addHeader("Authorization", "Bearer $newToken")
                        .build()
                    return chain.proceed(request)
                }
            }
        }

        return response
    }

    private fun refreshAccessToken(): String? {
        val refreshToken = MyPreferences.getRefreshToken(context) ?: return null

        val retrofit = Retrofit.Builder()
            .baseUrl("http://ec2-52-78-152-109.ap-northeast-2.compute.amazonaws.com:8000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val api = retrofit.create(ApiService::class.java)
        val call = api.refreshAccessToken(mapOf("refresh" to refreshToken))
        val response = call.execute()

        return if (response.isSuccessful) {
            response.body()?.accessToken
        } else {
            null
        }
    }
}
