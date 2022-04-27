package com.insertu.instagramclonekotlin.profile.view

import android.os.Bundle
import android.view.*
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.insertu.instagramclonekotlin.R
import com.insertu.instagramclonekotlin.comum.base.BaseFragment
import com.insertu.instagramclonekotlin.databinding.FragmentProfileBinding

class ProfileFragment :
    BaseFragment<FragmentProfileBinding, Profile.Presenter>
        (
        R.layout.fragment_profile,
        FragmentProfileBinding::bind
    ) {

    override lateinit var presenter: Profile.Presenter

    override fun setupView() {
        binding?.profileRvFotos?.layoutManager = GridLayoutManager(requireContext(), 3)
        binding?.profileRvFotos?.adapter = PostAdapter()
    }

    override fun getMenu(): Int {
        return R.layout.fragment_profile
    }

    override fun setupPresenter() {
        //TODO: presenter(this, repository
    }


}