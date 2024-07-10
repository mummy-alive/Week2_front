package com.example.bottomnavigationviewtest.models

import com.example.bottomnavigationviewtest.models.profile.Profile
import com.example.bottomnavigationviewtest.models.profile.TransformedProfile
import com.example.bottomnavigationviewtest.models.recruitpost.RecruitPost
import com.google.gson.annotations.SerializedName

data class HomeDataResponse(
    @SerializedName("recent_posts") val recentPosts: List<RecruitPost>,
    @SerializedName("profiles") val profiles: List<TransformedProfile>
)
