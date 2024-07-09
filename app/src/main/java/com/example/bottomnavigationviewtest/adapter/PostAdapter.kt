package com.example.bottomnavigationviewtest.ui.post

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bottomnavigationviewtest.databinding.ItemRecruitPostBinding
import com.example.bottomnavigationviewtest.models.recruitpost.RecruitPost

class PostAdapter(private var posts: List<RecruitPost>) : RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding = ItemRecruitPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(posts[position])
    }

    fun updatePosts(newPosts: List<RecruitPost>) {
        posts = newPosts
        notifyDataSetChanged()
    }

    override fun getItemCount() = posts.size

    inner class PostViewHolder(private val binding: ItemRecruitPostBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(post: RecruitPost) {
            binding.postTitle.text = post.title
            binding.postContent.text = post.content
            binding.postWriter.text = post.writer.username
            binding.postDate.text = post.created_at
            binding.postTechTag.text = post.tech_tags.joinToString(", ") { it.tech_tag_name }
        }
    }
}
