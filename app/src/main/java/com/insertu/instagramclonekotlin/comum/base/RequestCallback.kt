package com.insertu.instagramclonekotlin.comum.base

interface RequestCallback<T> {

    fun onSucess( data: T)
    fun onFailure(message: String)
    fun onCompleted()
}