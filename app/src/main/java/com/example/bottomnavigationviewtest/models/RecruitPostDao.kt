/*
package com.example.bottomnavigationviewtest.models

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface RecruitPostDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(posts: List<RecruitPost>)

    @Query("SELECT * FROM recruit_post_table")
    suspend fun getAllPosts(): List<RecruitPost>
}*/
