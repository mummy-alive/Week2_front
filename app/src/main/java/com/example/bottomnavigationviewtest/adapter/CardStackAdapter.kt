package com.example.bottomnavigationviewtest.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bottomnavigationviewtest.databinding.ItemMatchingCardBinding
import com.example.bottomnavigationviewtest.models.Profile

class CardStackAdapter(
    val context: Context,
    val items: MutableList<Profile>
) : RecyclerView.Adapter<CardStackAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemMatchingCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(items[position])
    }

    override fun getItemCount(): Int = items.size

    inner class ViewHolder(private val binding: ItemMatchingCardBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindItems(item: Profile) {
            binding.textMbti.text = item.mbti
            binding.textInterest.text = item.interest/*
            binding.textTechTags.text = item.techTagList.joinToString { it.techTagName }*/
        }
    }
}
