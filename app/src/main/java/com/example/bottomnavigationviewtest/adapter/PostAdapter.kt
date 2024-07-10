package com.example.bottomnavigationviewtest.ui.matching

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bottomnavigationviewtest.R
import com.example.bottomnavigationviewtest.databinding.ItemRecruitPostBinding
import com.example.bottomnavigationviewtest.models.recruitpost.RecruitPost
import com.example.bottomnavigationviewtest.network.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PostAdapter(private var posts: List<RecruitPost>) : RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding = ItemRecruitPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(posts[position])
    }

    override fun getItemCount() = posts.size

    fun updatePosts(newPosts: List<RecruitPost>) {
        posts = newPosts
        notifyDataSetChanged()
    }

    inner class PostViewHolder(private val binding: ItemRecruitPostBinding) : RecyclerView.ViewHolder(binding.root) {
        private val postTitle: TextView = binding.postTitle
        private val postContent: TextView = binding.postContent
        private val postTechTag: TextView = binding.postTechTag
        private val postWriter: TextView = binding.postWriter
        private val postDate: TextView = binding.postDate
        private val imageHeartOff: ImageView = binding.imageHeartOff
        private val imageHeartOn: ImageView = binding.imageHeartOn

        fun bind(post: RecruitPost) {
            postTitle.text = post.title
            postContent.text = post.content
            postTechTag.text = post.tech_tags.joinToString(", ") { it.tech_tag_name }
            postWriter.text = post.writer.username
            postDate.text = post.created_at.split("T")[0]

            // 초기 스크랩 상태 설정
            checkScrapStatus(post.post_id)

            // 하트 오프 클릭 이벤트 처리
            imageHeartOff.setOnClickListener {
                addScrap(post.post_id)
            }

            // 하트 온 클릭 이벤트 처리
            imageHeartOn.setOnClickListener {
                removeScrap(post.post_id)
            }
        }

        // 스크랩 상태 확인 메서드
        private fun checkScrapStatus(postId: Int) {
            RetrofitInstance.api.getScrap(postId).enqueue(object : Callback<Boolean> {
                override fun onResponse(call: Call<Boolean>, response: Response<Boolean>) {
                    if (response.isSuccessful) {
                        val isScraped = response.body() ?: false
                        updateHeartIcon(isScraped)
                    }
                }

                override fun onFailure(call: Call<Boolean>, t: Throwable) {
                    // 실패 처리
                }
            })
        }

        // 하트 아이콘 업데이트 메서드
        private fun updateHeartIcon(isScraped: Boolean) {
            if (isScraped) {
                imageHeartOff.visibility = View.INVISIBLE
                imageHeartOn.visibility = View.VISIBLE
            } else {
                imageHeartOff.visibility = View.VISIBLE
                imageHeartOn.visibility = View.INVISIBLE
            }
        }

        // 스크랩 추가 메서드
        private fun addScrap(postId: Int) {
            RetrofitInstance.api.addScrap(postId).enqueue(object : Callback<Void> {
                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    if (response.isSuccessful) {
                        updateHeartIcon(true)
                    }
                }

                override fun onFailure(call: Call<Void>, t: Throwable) {
                    // 실패 처리
                }
            })
        }

        // 스크랩 제거 메서드
        private fun removeScrap(postId: Int) {
            RetrofitInstance.api.removeScrap(postId).enqueue(object : Callback<Void> {
                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    if (response.isSuccessful) {
                        updateHeartIcon(false)
                    }
                }

                override fun onFailure(call: Call<Void>, t: Throwable) {
                    // 실패 처리
                }
            })
        }
    }
}
