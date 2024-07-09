package com.example.bottomnavigationviewtest.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bottomnavigationviewtest.R
import com.example.bottomnavigationviewtest.models.UserBlockResponse

class BlockAdapter (
    private val likes: List<UserBlockResponse>,
    private val onDeleteClick: (Int) -> Unit
) : RecyclerView.Adapter<BlockAdapter.BlockViewHolder>() {

    class BlockViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameText: TextView = view.findViewById(R.id.text_name)
        val trashIcon: ImageView = view.findViewById(R.id.imageTrash)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BlockViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_like, parent, false)
        return BlockViewHolder(view)
    }

    override fun onBindViewHolder(holder: BlockViewHolder, position: Int) {
        val profile = likes[position].to_id
        holder.nameText.text = profile.name

        holder.trashIcon.setOnClickListener {
            onDeleteClick(likes[position].id)
        }
    }

    override fun getItemCount() = likes.size
}