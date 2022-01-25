package com.insertu.instagramclonekotlin.login

import com.insertu.instagramclonekotlin.comum.base.BasePresenter
import com.insertu.instagramclonekotlin.comum.base.BaseView

interface Login {

    interface Presenter : BasePresenter{
        fun login(email: String, password: String)

    }

    interface View: BaseView<Presenter>{
        fun showProgressBar(enabled: Boolean)
        fun displayEmailFailure(emailError: Int?)
        fun displayPasswordFailure(passwordError: Int?)
        fun authLoginSucessful()
        fun authLoginFailure(message: String)


    }
}