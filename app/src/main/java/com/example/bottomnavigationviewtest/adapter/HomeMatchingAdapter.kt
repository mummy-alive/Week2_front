package com.example.bottomnavigationviewtest.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bottomnavigationviewtest.databinding.ItemHomeMatchingBinding
import com.example.bottomnavigationviewtest.models.Profile

class HomeMatchingAdapter (private val profiles: List<Profile>) :
    RecyclerView.Adapter<HomeMatchingAdapter.MatchingViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchingViewHolder {
        val binding = ItemHomeMatchingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MatchingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MatchingViewHolder, position: Int) {
        holder.bind(profiles[position])
    }

    override fun getItemCount(): Int = profiles.size

    class MatchingViewHolder(private val binding: ItemHomeMatchingBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(profile : Profile) {
            binding.matchingName.text = profile.name
            binding.matchingMbti.text = profile.mbti
            binding.matchingInterest.text = profile.interest
            // tech
            // image
        }
    }
}