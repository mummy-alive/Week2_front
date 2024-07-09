package com.example.bottomnavigationviewtest.ui.matching

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bottomnavigationviewtest.R
import com.example.bottomnavigationviewtest.databinding.ItemMatchingCardBinding
import com.example.bottomnavigationviewtest.models.profile.Profile

class CardAdapter(private var profiles: List<Profile>) : RecyclerView.Adapter<CardAdapter.CardViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val binding = ItemMatchingCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CardViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        holder.bind(profiles[position])
    }

    override fun getItemCount() = profiles.size

    fun updateProfiles(newProfiles: List<Profile>) {
        profiles = newProfiles
        notifyDataSetChanged()
    }

    inner class CardViewHolder(private val binding: ItemMatchingCardBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(profile: Profile) {
            binding.textMbti.text = profile.mbti
            binding.textInterest.text = profile.interest
            binding.textClass.text = profile.class_tag.toString()

            // 기술 태그들을 띄어쓰기로 구분된 문자열로 설정
            val techTagsString = profile.tech_tags.joinToString(" ") { it.tech_tag_name }
            binding.textTech.text = techTagsString
        }
    }
}

