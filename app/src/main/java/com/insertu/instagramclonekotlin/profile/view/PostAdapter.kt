package com.insertu.instagramclonekotlin.profile.view

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.insertu.instagramclonekotlin.R
import com.insertu.instagramclonekotlin.comum.model.Post

class PostAdapter : RecyclerView.Adapter<PostAdapter.postViewHolder>() {

    var itens: List<Post> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): postViewHolder {
        return postViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_profile_grid, parent, false)
        )
    }

    override fun onBindViewHolder(holder: postViewHolder, position: Int) {
        holder.bind(itens[position].uri)
    }

    override fun getItemCount(): Int {
        return 30
    }

    class postViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(imagem: Uri) {
            itemView.findViewById<ImageView>(R.id.item_profile_img_grid)
                .setImageURI(imagem)
        }
    }

}