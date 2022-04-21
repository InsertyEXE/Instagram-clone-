package com.insertu.instagramclonekotlin.cadastro.data

import android.net.Uri
import android.os.Handler
import android.os.Looper
import android.provider.ContactsContract
import com.insertu.instagramclonekotlin.comum.model.Database
import com.insertu.instagramclonekotlin.comum.model.Photo
import com.insertu.instagramclonekotlin.comum.model.UserAuth
import java.util.*


class FakeRegisterDataSource: RegisterDataSource {

    override fun create(
        email: String,
        callback: RegisterEmailCallback
    ) {

        Handler(Looper.getMainLooper()).postDelayed({

           val userAuth = Database.usersAuth.firstOrNull { email == it.email }

            if (userAuth == null){
                callback.onSucess()
            } else {
                callback.onFailure("Email já cadastrado")
            }

            callback.onCompleted()
        }, 2000)

    }

    override fun create(
        email: String,
        name: String,
        password: String,
        callback: RegisterSenhaCallback
    ) {

        Handler(Looper.getMainLooper()).postDelayed({

            val userAuth = Database.usersAuth.firstOrNull { email == it.email }

            if (userAuth != null){
                callback.onFailure("Usuario já cadastrado")
            } else {

                val newUser = UserAuth(UUID.randomUUID().toString(), name, email, password)

                val create = Database.usersAuth.add(newUser)

                if (create){

                    Database.sessionAuth = newUser
                    callback.onSucess(email, name)

                }

                else
                    callback.onFailure("Houve um problema no servidor.")
            }

            callback.onCompleted()
        }, 2000)

    }

    override fun updateUser(photo: Uri, callback: RegisterEmailCallback) {


        Handler(Looper.getMainLooper()).postDelayed({

            val userAuth = Database.sessionAuth

            if (userAuth == null){
                callback.onFailure("Usuario Não encontrado")
            } else {

                val newPhoto = Photo(photo, userAuth.uuid)

                val create = Database.photo.add(newPhoto)

                if (create)
                    callback.onSucess()
                else
                    callback.onFailure("Houve um problema no servidor.")
            }

            callback.onCompleted()
        }, 2000)
    }


}


