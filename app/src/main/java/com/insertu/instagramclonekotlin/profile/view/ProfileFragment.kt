package com.insertu.instagramclonekotlin.profile.view

import android.os.Bundle
import android.view.*
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.insertu.instagramclonekotlin.R

class ProfileFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val rvGrid = view.findViewById<RecyclerView>(R.id.profile_rv_fotos)
        rvGrid.layoutManager = GridLayoutManager(requireContext(), 3)
        rvGrid.adapter = postAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_profile, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }


    private class postAdapter : RecyclerView.Adapter<postAdapter.postViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): postViewHolder {
            return postViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_profile_grid, parent, false)
            )
        }

        override fun onBindViewHolder(holder: postViewHolder, position: Int) {
            holder.bind(R.drawable.ic_insta_add)
        }

        override fun getItemCount(): Int {
            return 30
        }

        private class postViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
            fun bind(imagem: Int){
                itemView.findViewById<ImageView>(R.id.item_profile_img_grid).setImageResource(imagem)
            }
        }

    }
}