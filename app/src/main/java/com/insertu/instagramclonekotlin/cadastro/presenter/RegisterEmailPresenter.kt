package com.insertu.instagramclonekotlin.cadastro.presenter

import android.util.Patterns
import com.insertu.instagramclonekotlin.R
import com.insertu.instagramclonekotlin.cadastro.RegisterView
import com.insertu.instagramclonekotlin.cadastro.data.RegisterEmailCallback
import com.insertu.instagramclonekotlin.cadastro.data.RegisterRepository

class RegisterEmailPresenter(
    private var view: RegisterView.View?,
    private var dataSource: RegisterRepository): RegisterView.Presenter {

    override fun create(email: String) {

        val isEmailValid = Patterns.EMAIL_ADDRESS.matcher(email).matches()

        if (!isEmailValid){
            view?.displayEmailError(R.string.invalid_email)
        } else {
            view?.displayEmailError(null)
        }

        if (isEmailValid){
            view?.showProgressBar(true)

            dataSource.create(email, object : RegisterEmailCallback{
                override fun onSucess() {
                    view?.goToNameAndpassword(email)
                }

                override fun onFailure(message: String) {
                    view?.onEmailFailure(message)
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