package com.example.bottomnavigationviewtest.ui.matching

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bottomnavigationviewtest.databinding.ItemHomeMatchingBinding
import com.example.bottomnavigationviewtest.models.profile.Profile

class HomeMatchingAdapter(private var profiles: List<Profile>) : RecyclerView.Adapter<HomeMatchingAdapter.HomeMatchingViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeMatchingViewHolder {
        val binding = ItemHomeMatchingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeMatchingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeMatchingViewHolder, position: Int) {
        holder.bind(profiles[position])
    }

    override fun getItemCount() = profiles.size

    fun updateProfiles(newProfiles: List<Profile>) {
        profiles = newProfiles
        notifyDataSetChanged()
    }

    inner class HomeMatchingViewHolder(private val binding: ItemHomeMatchingBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(profile: Profile) {
            binding.matchingMbti.text = profile.mbti
            binding.matchingInterest.text = profile.interest
            binding.matchingTech.text = profile.tech_tags.toString()
            // 이미지 로드 로직을 여기에 추가
        }
    }
}
