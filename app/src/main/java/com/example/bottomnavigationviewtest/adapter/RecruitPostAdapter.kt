package com.example.bottomnavigationviewtest.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.RecyclerListener
import com.example.bottomnavigationviewtest.R
import com.example.bottomnavigationviewtest.databinding.ItemRecruitPostBinding
import com.example.bottomnavigationviewtest.models.RecruitPost

class RecruitPostAdapter(private var recruitPosts: List<RecruitPost>) :
    RecyclerView.Adapter<RecruitPostAdapter.RecruitPostViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecruitPostViewHolder {
        val binding = ItemRecruitPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecruitPostViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecruitPostViewHolder, position: Int) {
        holder.bind(recruitPosts[position])
    }

    override fun getItemCount(): Int = recruitPosts.size

    fun updateData(newRecruitPosts: List<RecruitPost>) {
        recruitPosts = newRecruitPosts
        notifyDataSetChanged()
    }

    class RecruitPostViewHolder(private val binding: ItemRecruitPostBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(recruitPost: RecruitPost) {
            binding.postTitle.text = recruitPost.title
            binding.postContent.text = recruitPost.content
            binding.postWriter.text = recruitPost.writer.name
            binding.postDate.text = recruitPost.createdDate
        }
    }
}