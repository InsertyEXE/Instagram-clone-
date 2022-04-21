package com.insertu.instagramclonekotlin.cadastro.presenter

import android.net.Uri
import android.util.Patterns
import com.insertu.instagramclonekotlin.R
import com.insertu.instagramclonekotlin.cadastro.RegisterPhoto
import com.insertu.instagramclonekotlin.cadastro.RegisterView
import com.insertu.instagramclonekotlin.cadastro.data.RegisterEmailCallback
import com.insertu.instagramclonekotlin.cadastro.data.RegisterRepository

class RegisterPhotoPresenter(
    private var view: RegisterPhoto.View?,
    private var dataSource: RegisterRepository): RegisterPhoto.Presenter {

    override fun update(foto: Uri) {

        view?.showProgressBar(true)
        dataSource.updateUser(foto, object: RegisterEmailCallback {


            override fun onSucess() {
                view?.onUpdateSucess()
            }

            override fun onFailure(message: String) {
                view?.onUpdateFailure(message)
            }

            override fun onCompleted() {
                view?.showProgressBar(false)
            }

        })

    }



    override fun onDestroy() {
        view = null
    }



}




