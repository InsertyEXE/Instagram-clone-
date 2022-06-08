package com.insertu.instagramclonekotlin.comum.model

import java.util.*

object Database {

    val usersAuth = hashSetOf<UserAuth>()
    val photo = hashSetOf<Photo>()
    val posts = hashMapOf<String, Set<Post>>()

    var sessionAuth: UserAuth? = null

    init {
        usersAuth.add(UserAuth(UUID.randomUUID().toString(), "UserA", "a@gmail.com", "12345678"))
        usersAuth.add(UserAuth(UUID.randomUUID().toString(), "UserB", "b@gmail.com", "87654321"))

        sessionAuth = usersAuth.first()
    }
}