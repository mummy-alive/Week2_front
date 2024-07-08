package com.example.bottomnavigationviewtest.models

data class Chat(
    val id: String,
    val senderId: String,
    val receiverId: String,
    val content: String,
    val sentDate: String,
    val originPostId: String
)
