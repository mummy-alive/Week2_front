package com.example.bottomnavigationviewtest.models

data class Profile (
    // 이름은 익명이라 제외
    val email : String, // primary key
    val class_tag : Int,
    val mbti: String,
    val interest: String,
    val isRecruit: Boolean,
    val techTagList: List<TechTag>,
    /*    val img_url : Int,*/
)