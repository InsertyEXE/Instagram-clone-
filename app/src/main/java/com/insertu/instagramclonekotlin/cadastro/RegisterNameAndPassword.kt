package com.insertu.instagramclonekotlin.cadastro

import androidx.annotation.StringRes
import com.insertu.instagramclonekotlin.comum.base.BasePresenter
import com.insertu.instagramclonekotlin.comum.base.BaseView

interface RegisterNameAndPassword {

    interface Presenter: BasePresenter {
        fun create(email: String, nome: String, password: String, confirm: String)
    }

    interface View: BaseView<Presenter> {

        fun showProgressBar(enable: Boolean)
        fun displayNameError(@StringRes nameError: Int?)
        fun displayPasswordError(@StringRes passwordError: Int?)
        fun onCreateFailure(message: String)
        fun onCreateSucess(email: String, name: String)

    }
}