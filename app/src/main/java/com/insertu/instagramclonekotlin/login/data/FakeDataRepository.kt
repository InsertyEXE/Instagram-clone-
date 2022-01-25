package com.insertu.instagramclonekotlin.login.data

import android.os.Handler
import android.os.Looper
import com.insertu.instagramclonekotlin.comum.model.Database


class FakeDataRepository: LoginDataSource {
    override fun login(email: String, password: String, callback: LoginCallback) {
        Handler(Looper.getMainLooper()).postDelayed({

           val userAuth = Database.usersAuth.firstOrNull { email == it.email }

            when {
                userAuth == null -> {
                    callback.onFailure("Usuario não encontrado")
                }
                userAuth.password != password -> {
                    callback.onFailure("Senha incorreta")
                }
                else -> {
                    Database.sessionAuth = userAuth
                    callback.onSucess(userAuth)
                }
            }

            callback.onCompleted()
        }, 2000)

    }

}


