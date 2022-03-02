package com.insertu.instagramclonekotlin.cadastro.presenter

import com.insertu.instagramclonekotlin.R
import com.insertu.instagramclonekotlin.cadastro.RegisterNameAndPassword
import com.insertu.instagramclonekotlin.cadastro.data.RegisterRepository
import com.insertu.instagramclonekotlin.cadastro.data.RegisterSenhaCallback

class RegisterSenhaPresenter(

    private var view: RegisterNameAndPassword.View?,
    private val repository: RegisterRepository

) : RegisterNameAndPassword.Presenter {


    override fun create(email: String, nome: String, password: String, confirm: String) {

        val isValidPassoword = password.length >= 8
        val isValidName = nome.length > 3
        val isConfirmedPassword = confirm == password


        if (!isValidName) view?.displayNameError(R.string.invalid_nome)
        else view?.displayNameError(null)


        if (!isValidPassoword) view?.displayPasswordError(R.string.invalid_password)
        else {
            view?.displayPasswordError(null)

            if (!isConfirmedPassword) view?.displayPasswordError(R.string.password_not_equal)
            else view?.displayPasswordError(null)
        }





        if (isValidName && isValidPassoword && isConfirmedPassword){

            view?.showProgressBar(true)

            repository.create(email, nome, password, object : RegisterSenhaCallback {

                override fun onSucess(email: String, nome: String) {
                    view?.onCreateSucess(email, nome)
                }

                override fun onFailure(message: String) {
                    view?.onCreateFailure(message)
                }

                override fun onCompleted() {
                    view?.showProgressBar(false)
                }

            })


        }
    }

    override fun onDestroy() {
       view = null
    }
}