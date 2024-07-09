package com.example.bottomnavigationviewtest.adapter

import android.content.Context
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import com.example.bottomnavigationviewtest.R
import com.example.bottomnavigationviewtest.models.recruitpost.RecruitPost

class HomeRecruitAdapter(private val context: Context, private val recruitPosts: List<RecruitPost>){

    fun addPostsToContainer(container: LinearLayout) {
        val inflater = LayoutInflater.from(context)
        val topPosts = recruitPosts.take(4) // 상위 네 개의 포스트만 가져오기
        for (post in topPosts) {
            val postView = inflater.inflate(R.layout.item_recruit_post, container, false)
            postView.findViewById<TextView>(R.id.postTitle).text = post.title
            postView.findViewById<TextView>(R.id.postContent).text = post.content
            postView.findViewById<TextView>(R.id.postDate).text = post.createdDate
            postView.findViewById<TextView>(R.id.postWriter).text = post.writer
            container.addView(postView)
        }
    }
}