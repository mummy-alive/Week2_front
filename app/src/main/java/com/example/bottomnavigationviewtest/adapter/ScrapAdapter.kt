package com.example.bottomnavigationviewtest.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bottomnavigationviewtest.R
import com.example.bottomnavigationviewtest.models.recruitpost.RecruitPost

class ScrapAdapter(private var posts: List<RecruitPost>) : RecyclerView.Adapter<ScrapAdapter.ScrapViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScrapViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_recruit_post, parent, false)
        return ScrapViewHolder(view)
    }

    override fun onBindViewHolder(holder: ScrapViewHolder, position: Int) {
        holder.bind(posts[position])
    }

    override fun getItemCount() = posts.size

    fun updatePosts(newPosts: List<RecruitPost>) {
        posts = newPosts
        notifyDataSetChanged()
    }

    inner class ScrapViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val postTitleTextView: TextView = itemView.findViewById(R.id.postTitle)
        private val postContentTextView: TextView = itemView.findViewById(R.id.postContent)
        private val postWriterTextView: TextView = itemView.findViewById(R.id.postWriter)
        private val postDateTextView: TextView = itemView.findViewById(R.id.postDate)
        private val postTechTagTextView: TextView = itemView.findViewById(R.id.postTechTag)

        fun bind(post: RecruitPost) {
            postTitleTextView.text = post.title
            postContentTextView.text = post.content
            postWriterTextView.text = post.writer.username
            postDateTextView.text = post.created_at
            postTechTagTextView.text = post.tech_tags.joinToString(", ") { it.tech_tag_name }
        }
    }
}
