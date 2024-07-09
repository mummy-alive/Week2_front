package com.example.bottomnavigationviewtest.models.recruitpost

import com.example.bottomnavigationviewtest.models.TechTag

data class RecruitPost(
    val content: String,
    val created_at: String,
    val post_id: Int,
    val post_tag: Int,
    val tech_tags: List<TechTag>,
    val title: String,
    val writer: Writer
)

data class Writer(
    val id: Int,
    val username: String
)