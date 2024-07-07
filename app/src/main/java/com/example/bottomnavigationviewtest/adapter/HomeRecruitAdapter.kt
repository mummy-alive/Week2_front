package com.example.bottomnavigationviewtest.adapter

import android.content.Context
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import com.example.bottomnavigationviewtest.R
import com.example.bottomnavigationviewtest.models.RecruitPost

class HomeRecruitAdapter(private val context: Context, private val recruitPosts: List<RecruitPost>){

    fun addPostsToContainer(container: LinearLayout) {
        val inflater = LayoutInflater.from(context)
        for (post in recruitPosts) {
            val postView = inflater.inflate(R.layout.item_recruit_post, container, false)
            postView.findViewById<TextView>(R.id.postTitle).text = post.title
            postView.findViewById<TextView>(R.id.postContent).text = post.content
            postView.findViewById<TextView>(R.id.postDate).text = post.date
            postView.findViewById<TextView>(R.id.postWriter).text = post.writer
//            postView.findViewById<TextView>(R.id.postTitle).text = post.title
            container.addView(postView)
        }
    }
}