package com.example.bottomnavigationviewtest.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bottomnavigationviewtest.databinding.ItemHomeMatchingBinding
import com.example.bottomnavigationviewtest.models.MatchingProfile

class HomeMatchingAdapter (private val matchingProfiles: List<MatchingProfile>) :
    RecyclerView.Adapter<HomeMatchingAdapter.MatchingViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchingViewHolder {
        val binding = ItemHomeMatchingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MatchingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MatchingViewHolder, position: Int) {
        holder.bind(matchingProfiles[position])
    }

    override fun getItemCount(): Int = matchingProfiles.size

    class MatchingViewHolder(private val binding: ItemHomeMatchingBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(matchingProfile : MatchingProfile) {
            binding.matchingName.text = matchingProfile.name
            binding.matchingMbti.text = matchingProfile.mbti
            binding.matchingInterest.text = matchingProfile.interest
            // tech
            // image
        }
    }
}