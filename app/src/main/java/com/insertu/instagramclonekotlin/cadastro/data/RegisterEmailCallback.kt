package com.insertu.instagramclonekotlin.cadastro.data

import com.insertu.instagramclonekotlin.comum.model.UserAuth

interface RegisterEmailCallback {
    fun onSucess()
    fun onFailure(message: String)
    fun onCompleted()

}
