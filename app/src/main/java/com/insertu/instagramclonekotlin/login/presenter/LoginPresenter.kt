package com.insertu.instagramclonekotlin.login.presenter

import android.util.Patterns
import com.insertu.instagramclonekotlin.R
import com.insertu.instagramclonekotlin.comum.model.UserAuth
import com.insertu.instagramclonekotlin.login.Login
import com.insertu.instagramclonekotlin.login.data.LoginCallback
import com.insertu.instagramclonekotlin.login.data.LoginRepository

class LoginPresenter(
    private var view: Login.View?,
    private val repository: LoginRepository
) : Login.Presenter {

    override fun login(email: String, password: String) {
        val isEmailValid = Patterns.EMAIL_ADDRESS.matcher(email).matches()
        val isPasswordValid = password.length >= 8

        if (!isEmailValid){
            view?.displayEmailFailure(R.string.invalid_email)
        }
        else {
            view?.displayEmailFailure(null)
        }

        if (!isPasswordValid){
            view?.displayPasswordFailure(R.string.invalid_password)
        }
        else{
            view?.displayPasswordFailure(null)
        }

        if (isEmailValid && isPasswordValid){
            view?.showProgressBar(true)

            repository.login(
                email,
                password,
                object : LoginCallback
                {
                    override fun onSucess(userAuth: UserAuth) {
                        view?.authLoginSucessful()
                    }

                    override fun onFailure(message: String) {
                        view?.authLoginFailure(message)
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