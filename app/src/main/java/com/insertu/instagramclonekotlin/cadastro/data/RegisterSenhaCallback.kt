package com.insertu.instagramclonekotlin.cadastro.data

interface RegisterSenhaCallback {
    fun onSucess( email: String, name: String)
    fun onFailure(message: String)
    fun onCompleted()
}