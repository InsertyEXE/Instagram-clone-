package com.insertu.instagramclonekotlin.profile.data

import android.os.Handler
import android.os.Looper
import com.insertu.instagramclonekotlin.comum.base.RequestCallback
import com.insertu.instagramclonekotlin.comum.model.Database
import com.insertu.instagramclonekotlin.comum.model.Post
import com.insertu.instagramclonekotlin.comum.model.UserAuth
import java.util.*

class ProfileFakeDataSource: ProfileDataSource {

    override fun fetchUserProfile(userUUID: String, callback: RequestCallback<UserAuth>) {
        Handler(Looper.getMainLooper()).postDelayed({

            val userAuth = Database.usersAuth.firstOrNull { userUUID == it.uuid }

            if (userAuth != null){
                callback.onSucess(userAuth)
            } else {
                callback.onFailure("Usuario não encontrado")
            }

            callback.onCompleted()
        }, 2000)

    }

    override fun fetchUserPost(userUUID: String, callback: RequestCallback<List<Post>>) {

        Handler(Looper.getMainLooper()).postDelayed({

            val posts = Database.posts[userUUID]

            callback.onSucess(posts?.toList() ?: emptyList())
            callback.onCompleted()
        }, 2000)

    }

}