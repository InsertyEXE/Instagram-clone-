package com.insertu.instagramclonekotlin.cadastro

import androidx.annotation.StringRes
import com.insertu.instagramclonekotlin.comum.base.BasePresenter
import com.insertu.instagramclonekotlin.comum.base.BaseView

interface RegisterView {

    interface Presenter: BasePresenter{
        fun create(email: String)
    }

    interface View: BaseView<Presenter> {

        fun showProgressBar(enable: Boolean)
        fun displayEmailError(@StringRes emailError: Int?)
        fun onEmailFailure(message: String)
        fun goToNameAndpassword(email: String)

    }
}