package com.insertu.instagramclonekotlin.profile.data

import com.insertu.instagramclonekotlin.comum.base.RequestCallback
import com.insertu.instagramclonekotlin.comum.model.Post
import com.insertu.instagramclonekotlin.comum.model.UserAuth
import java.util.*

interface ProfileDataSource {

    fun fetchUserProfile(userUUID: String, callback: RequestCallback<UserAuth>)

    fun fetchUserPost(userUUID: String, callback: RequestCallback<List<Post>>)

}