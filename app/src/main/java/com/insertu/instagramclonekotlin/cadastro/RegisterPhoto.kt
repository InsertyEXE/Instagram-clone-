package com.insertu.instagramclonekotlin.cadastro

import android.net.Uri
import androidx.annotation.StringRes
import com.insertu.instagramclonekotlin.comum.base.BasePresenter
import com.insertu.instagramclonekotlin.comum.base.BaseView

interface RegisterPhoto {

    interface Presenter: BasePresenter {
        fun update(foto: Uri)
    }

    interface View: BaseView<Presenter> {

        fun showProgressBar(enable: Boolean)
        fun onUpdateFailure(message: String)
        fun onUpdateSucess()

    }
}