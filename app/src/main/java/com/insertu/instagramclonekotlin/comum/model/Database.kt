package com.insertu.instagramclonekotlin.comum.model

import java.util.*

object Database {

     val usersAuth = hashSetOf<UserAuth>()

    var sessionAuth: UserAuth? = null

    init {
        usersAuth.add(UserAuth(UUID.randomUUID().toString(), "a@gmail.com", "12345678"))
        usersAuth.add(UserAuth(UUID.randomUUID().toString(), "b@gmail.com", "87654321"))

    }
}