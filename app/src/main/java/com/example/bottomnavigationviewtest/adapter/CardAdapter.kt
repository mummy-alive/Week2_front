package com.example.bottomnavigationviewtest.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bottomnavigationviewtest.databinding.ItemMatchingCardBinding
import com.example.bottomnavigationviewtest.models.profile.TransformedProfile

class CardAdapter(private var profiles: List<TransformedProfile>) : RecyclerView.Adapter<CardAdapter.CardViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val binding = ItemMatchingCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CardViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        holder.bind(profiles[position])
    }

    override fun getItemCount() = profiles.size

    fun updateProfiles(newProfiles: List<TransformedProfile>) {
        profiles = newProfiles
        notifyDataSetChanged()
    }

    inner class CardViewHolder(private val binding: ItemMatchingCardBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(profile: TransformedProfile) {
            binding.textMbti.text = profile.mbti
            binding.textInterest.text = profile.interest
            binding.textClass.text = profile.classTagWithSuffix
            binding.textTech.text = profile.techTagsString
        }
    }
}

