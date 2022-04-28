package com.insertu.instagramclonekotlin.comum.model

data class UserAuth(
    val uuid: String,
    val name: String,
    val email: String,
    val password: String,
    val countPost: Int,
    val countFollowing: Int,
    val countFollowers: Int
) {

}
