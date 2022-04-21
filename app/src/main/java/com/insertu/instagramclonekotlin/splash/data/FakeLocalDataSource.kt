package com.insertu.instagramclonekotlin.splash.data

import android.provider.ContactsContract
import com.insertu.instagramclonekotlin.comum.model.Database

class FakeLocalDataSource : SplashDataSource {
    override fun session(callback: SplashSessionCallback) {

        if (Database.sessionAuth != null)
            callback.onSucess()
        else
            callback.onFailure()

    }
}