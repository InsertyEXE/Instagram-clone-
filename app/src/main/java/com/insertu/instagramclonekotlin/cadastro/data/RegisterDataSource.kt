package com.insertu.instagramclonekotlin.cadastro.data

import android.net.Uri

interface RegisterDataSource {
    fun create(email: String, callback: RegisterEmailCallback)

    fun create(email: String, name: String, password: String, callback: RegisterSenhaCallback)

    fun updateUser(photo: Uri, callback: RegisterEmailCallback)

}
