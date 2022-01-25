package com.insertu.instagramclonekotlin.comum.base

import com.insertu.instagramclonekotlin.login.data.FakeDataRepository
import com.insertu.instagramclonekotlin.login.data.LoginRepository

object DependencyInjector {
    fun loginRepository() : LoginRepository{
        return LoginRepository(FakeDataRepository())
    }

}