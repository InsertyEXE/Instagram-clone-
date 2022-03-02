package com.insertu.instagramclonekotlin.cadastro.data

interface RegisterDataSource {
    fun create(email: String, callback: RegisterEmailCallback)

    fun create(email: String, name: String, password: String, callback: RegisterSenhaCallback)

}
