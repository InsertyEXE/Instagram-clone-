package com.insertu.instagramclonekotlin.profile.data

import com.insertu.instagramclonekotlin.comum.base.RequestCallback
import com.insertu.instagramclonekotlin.comum.model.Post
import com.insertu.instagramclonekotlin.comum.model.UserAuth
import java.util.*

class ProfileRepository(private val datasource: ProfileDataSource) {

    fun fetchUserProfile(userUUID: String, callback: RequestCallback<UserAuth>){
        datasource.fetchUserProfile(userUUID, callback)
    }

    fun fetchUserPosts(userUUID: String, callback: RequestCallback<List<Post>>){
        datasource.fetchUserPost(userUUID, callback)
    }
}