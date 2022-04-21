package com.insertu.instagramclonekotlin.splash

import com.insertu.instagramclonekotlin.comum.base.BasePresenter
import com.insertu.instagramclonekotlin.comum.base.BaseView

interface Splash {

    interface Presenter: BasePresenter{
        fun autheticated()
    }

    interface View: BaseView<Presenter>{
        fun goToMainScreen()
        fun goToLoginScreen()
    }
}