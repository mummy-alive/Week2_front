package com.example.bottomnavigationviewtest.models

data class RecruitPost(
    val id: Int,
    val title: String,
    val writer: User,
    val content: String,
    val createdDate: String
)
