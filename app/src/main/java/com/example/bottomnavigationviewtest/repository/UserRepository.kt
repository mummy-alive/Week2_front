package com.example.bottomnavigationviewtest.repository

import com.example.bottomnavigationviewtest.models.User
import com.example.bottomnavigationviewtest.network.RetrofitInstance
import retrofit2.Call

object UserRepository {
    fun createUser(token: String, user: User): Call<Boolean> {
        return RetrofitInstance.api.createUser("Bearer $token", user)
    }

    fun getUserByEmail(token: String, email: String): Call<User> {
        return RetrofitInstance.api.getUserByEmail("Bearer $token", email)
    }
}