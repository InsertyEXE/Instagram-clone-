package com.insertu.instagramclonekotlin.cadastro.data

class RegisterRepository(private val dataSource: RegisterDataSource) {

    fun create(email: String,  callback: RegisterEmailCallback){
        dataSource.create(email, callback)
    }

    fun create(email: String, name: String, password: String, callback: RegisterSenhaCallback){
        dataSource.create(email, name, password, callback)
    }

}