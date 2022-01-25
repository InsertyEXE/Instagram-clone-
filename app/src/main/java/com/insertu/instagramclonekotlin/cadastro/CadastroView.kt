package com.insertu.instagramclonekotlin.cadastro

import androidx.annotation.StringRes
import com.insertu.instagramclonekotlin.comum.base.BasePresenter
import com.insertu.instagramclonekotlin.comum.base.BaseView

interface CadastroView {

    interface Presenter: BasePresenter{
        fun create(email: String)
    }

    interface View: BaseView<Presenter> {
        fun displayEmailError(@StringRes emailError: Int?)

    }
}