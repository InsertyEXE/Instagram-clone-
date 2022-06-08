package com.insertu.instagramclonekotlin.profile.view

import android.os.Bundle
import android.view.*
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.insertu.instagramclonekotlin.R
import com.insertu.instagramclonekotlin.comum.base.BaseFragment
import com.insertu.instagramclonekotlin.comum.base.DependencyInjector
import com.insertu.instagramclonekotlin.comum.model.Post
import com.insertu.instagramclonekotlin.comum.model.UserAuth
import com.insertu.instagramclonekotlin.databinding.FragmentProfileBinding
import com.insertu.instagramclonekotlin.profile.Profile
import com.insertu.instagramclonekotlin.profile.presenter.ProfilePresenter

class ProfileFragment :
    BaseFragment<FragmentProfileBinding, Profile.Presenter>
        (
        R.layout.fragment_profile,
        FragmentProfileBinding::bind
    ), Profile.View {

    private val adapter = PostAdapter()
    override lateinit var presenter: Profile.Presenter

    override fun setupView() {
        binding?.profileRvFotos?.layoutManager = GridLayoutManager(requireContext(), 3)
        binding?.profileRvFotos?.adapter = adapter

        presenter.fetchUserProfile()
    }

    override fun setupPresenter() {
        val repository = DependencyInjector.profileRepository()
        presenter = ProfilePresenter(this, repository)
    }

    override fun showProgressBar(enabled: Boolean) {
        binding?.profileProgress?.visibility = if (enabled) View.VISIBLE else View.INVISIBLE
    }

    override fun displayUserProfile(userauth: UserAuth) {
        binding?.profileTxtPostCount?.text = userauth.countPost.toString()
        binding?.profileTxtFollowingCount?.text = userauth.countFollowing.toString()
        binding?.profileTxtFollowersCount?.text = userauth.countFollowers.toString()
        binding?.profileTxtUsername?.text = userauth.name
        binding?.profileTxtBio?.text = " BIO"
        presenter.fetchUserPost()
    }

    override fun displayRequestFailure(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    override fun displayEmptyPost() {
        binding?.profileEmptyPost?.visibility = View.VISIBLE
        binding?.profileRvFotos?.visibility = View.GONE
    }

    override fun displayFullpost(posts: List<Post>) {
        binding?.profileEmptyPost?.visibility = View.GONE
        binding?.profileRvFotos?.visibility = View.VISIBLE
        adapter.itens = posts
    }

    override fun getMenu(): Int {
        return R.menu.menu_profile
    }
}