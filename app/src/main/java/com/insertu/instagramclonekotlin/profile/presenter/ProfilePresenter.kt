package com.insertu.instagramclonekotlin.profile.presenter

import android.util.Patterns
import com.insertu.instagramclonekotlin.R
import com.insertu.instagramclonekotlin.comum.base.RequestCallback
import com.insertu.instagramclonekotlin.comum.model.Database
import com.insertu.instagramclonekotlin.comum.model.Post
import com.insertu.instagramclonekotlin.comum.model.UserAuth
import com.insertu.instagramclonekotlin.login.Login
import com.insertu.instagramclonekotlin.login.data.LoginCallback
import com.insertu.instagramclonekotlin.login.data.LoginRepository
import com.insertu.instagramclonekotlin.profile.Profile
import com.insertu.instagramclonekotlin.profile.data.ProfileRepository
import kotlinx.coroutines.channels.consumesAll

class ProfilePresenter(
    private var view: Profile.View?,
    private val repository: ProfileRepository
) : Profile.Presenter {

    override fun fetchUserProfile() {
        view?.showProgressBar(true)
        val userUUID = Database.sessionAuth?.uuid ?: throw RuntimeException("user not found")
        repository.fetchUserProfile(userUUID, object : RequestCallback<UserAuth>{
            override fun onSucess(data: UserAuth) {
                view?.displayUserProfile(data)
            }

            override fun onFailure(message: String) {
                view?.displayRequestFailure(message)
            }

            override fun onCompleted() {
            }

        })
    }

    override fun fetchUserPost() {
        val userUUID = Database.sessionAuth?.uuid ?: throw RuntimeException("user not found")
        repository.fetchUserPosts(userUUID, object: RequestCallback<List<Post>>{
            override fun onSucess(data: List<Post>) {

                if (!data.isEmpty())
                    view?.displayFullpost(data)
                else
                    view?.displayEmptyPost()
            }

            override fun onFailure(message: String) {
                view?.displayRequestFailure(message)
            }

            override fun onCompleted() {
                view?.showProgressBar(false)
            }

        })
    }

    override fun onDestroy() {
        view = null
    }
}