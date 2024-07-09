package com.example.bottomnavigationviewtest.adapter

import com.example.bottomnavigationviewtest.databinding.ItemMatchingCardBinding


import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bottomnavigationviewtest.models.profile.Profile

class CardStackAdapter(private val context: Context, private var profiles: List<Profile>) :
    RecyclerView.Adapter<CardStackAdapter.ViewHolder>() {

    fun setProfiles(newProfiles: List<Profile>) {
        profiles = newProfiles
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemMatchingCardBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(profiles[position])
    }

    override fun getItemCount(): Int = profiles.size

    inner class ViewHolder(private val binding: ItemMatchingCardBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(profile: Profile) {
            binding.textMbti.text = profile.mbti
            binding.textInterest.text = profile.interest
            // 필요한 프로필 데이터를 바인딩
        }
    }
}
