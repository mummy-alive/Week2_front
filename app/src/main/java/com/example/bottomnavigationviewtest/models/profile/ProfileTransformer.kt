package com.example.bottomnavigationviewtest.models.profile

object ProfileTransformer {
    fun transform(profile: Profile, techTagsMap: Map<Int, String>): TransformedProfile {
        val classTagWithSuffix = "${profile.class_tag}분반"
        val techTagsString = profile.tech_tags.mapNotNull { techTagsMap[it] }.joinToString(", ")

        return TransformedProfile(
            classTagWithSuffix = classTagWithSuffix,
            email = profile.email,
            interest = profile.interest,
            isRecruit = profile.is_recruit,
            mbti = profile.mbti,
            techTagsString = techTagsString
        )
    }

    fun transform(profiles: List<Profile>, techTagsMap: Map<Int, String>): List<TransformedProfile> {
        return profiles.map { transform(it, techTagsMap) }
    }
}
