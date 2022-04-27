package com.insertu.instagramclonekotlin.profile.view

import com.insertu.instagramclonekotlin.comum.base.BasePresenter
import com.insertu.instagramclonekotlin.comum.base.BaseView

interface Profile {

    interface Presenter : BasePresenter{
        fun fetchUserProfile()
        fun fetchUserPost()
    }

    interface View : BaseView<Presenter>{
        fun showProgressBar(enabled: Boolean)
        //TODO: outros metodos
    }

}