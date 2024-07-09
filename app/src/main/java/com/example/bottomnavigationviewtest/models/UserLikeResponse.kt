package com.example.bottomnavigationviewtest.models

data class UserLikeResponse(
    val id: Int,
    val from_id: String,
    val to_id: User
)