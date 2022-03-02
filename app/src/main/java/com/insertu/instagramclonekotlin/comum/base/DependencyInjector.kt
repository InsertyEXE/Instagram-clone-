package com.insertu.instagramclonekotlin.comum.base

import com.insertu.instagramclonekotlin.cadastro.data.FakeRegisterDataSource
import com.insertu.instagramclonekotlin.cadastro.data.RegisterRepository
import com.insertu.instagramclonekotlin.login.data.FakeDataRepository
import com.insertu.instagramclonekotlin.login.data.LoginRepository

object DependencyInjector {
    fun loginRepository() : LoginRepository{
        return LoginRepository(FakeDataRepository())
    }

    fun registerEmailRepository() : RegisterRepository{
        return RegisterRepository(FakeRegisterDataSource())
    }

}