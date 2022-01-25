package com.insertu.instagramclonekotlin.login.data

interface LoginDataSource {
    fun login(email: String, password: String, callback: LoginCallback)

}
