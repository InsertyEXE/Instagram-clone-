package com.insertu.instagramclonekotlin.login.data

import com.insertu.instagramclonekotlin.comum.model.UserAuth

interface LoginCallback {
    fun onSucess(userAuth : UserAuth)
    fun onFailure(message: String)
    fun onCompleted()

}
