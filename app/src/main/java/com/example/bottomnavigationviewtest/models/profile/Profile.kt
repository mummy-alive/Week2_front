package com.example.bottomnavigationviewtest.models.profile

import com.example.bottomnavigationviewtest.models.Email
import com.example.bottomnavigationviewtest.models.TechTag

data class Profile(
    val class_tag: Int,
    val email: Email,
    val interest: String,
    val is_recruit: Boolean,
    val mbti: String,
    val profile_id: Int? = null,
    val tech_tags: List<TechTag>
)