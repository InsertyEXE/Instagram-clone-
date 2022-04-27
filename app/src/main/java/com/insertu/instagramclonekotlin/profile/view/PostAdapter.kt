package com.insertu.instagramclonekotlin.profile.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.insertu.instagramclonekotlin.R

class PostAdapter : RecyclerView.Adapter<PostAdapter.postViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): postViewHolder {
        return postViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_profile_grid, parent, false)
        )
    }

    override fun onBindViewHolder(holder: postViewHolder, position: Int) {
        holder.bind(R.drawable.ic_insta_add)
    }

    override fun getItemCount(): Int {
        return 30
    }

    class postViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(imagem: Int) {
            itemView.findViewById<ImageView>(R.id.item_profile_img_grid)
                .setImageResource(imagem)
        }
    }

}