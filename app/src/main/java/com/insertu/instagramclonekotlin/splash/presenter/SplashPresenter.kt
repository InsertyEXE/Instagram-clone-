package com.insertu.instagramclonekotlin.splash.presenter

import com.insertu.instagramclonekotlin.splash.Splash
import com.insertu.instagramclonekotlin.splash.data.SplashDataRepository
import com.insertu.instagramclonekotlin.splash.data.SplashSessionCallback

class SplashPresenter(
    private var view: Splash.View?,
    private var repository: SplashDataRepository
) : Splash.Presenter {
    override fun autheticated() {

        repository.session(object : SplashSessionCallback {

            override fun onSucess() {
                view?.goToMainScreen()
            }

            override fun onFailure() {
                view?.goToLoginScreen()
            }

        })

    }

    override fun onDestroy() {
        view = null
    }
}