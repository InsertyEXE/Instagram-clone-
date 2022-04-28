package com.insertu.instagramclonekotlin.profile.view

import com.insertu.instagramclonekotlin.comum.base.BasePresenter
import com.insertu.instagramclonekotlin.comum.base.BaseView
import com.insertu.instagramclonekotlin.comum.model.Post
import com.insertu.instagramclonekotlin.comum.model.UserAuth

interface Profile {

    interface Presenter : BasePresenter{
        fun fetchUserProfile()
        fun fetchUserPost()
    }

    interface View : BaseView<Presenter>{
        fun showProgressBar(enabled: Boolean)
        fun displayUserProfile(userauth: UserAuth)
        fun displayRequestFailure(message: String)
        fun displayEmptyPost()
        fun displayFullpost(posts: List<Post>)
    }

}