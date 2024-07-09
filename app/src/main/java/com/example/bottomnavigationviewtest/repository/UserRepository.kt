package com.example.bottomnavigationviewtest.repository

import com.example.bottomnavigationviewtest.models.User
import com.example.bottomnavigationviewtest.network.RetrofitInstance
import retrofit2.Call

object UserRepository {
    fun createUser(user: User): Call<Boolean> {
        return RetrofitInstance.api.createUser(user)
    }

    fun getUserByEmail(email: String): Call<User> {
        return RetrofitInstance.api.getUserByEmail(email)
    }
}
