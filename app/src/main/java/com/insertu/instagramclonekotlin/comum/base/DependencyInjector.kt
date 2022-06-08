package com.insertu.instagramclonekotlin.comum.base

import com.insertu.instagramclonekotlin.cadastro.data.FakeRegisterDataSource
import com.insertu.instagramclonekotlin.cadastro.data.RegisterRepository
import com.insertu.instagramclonekotlin.login.data.FakeDataRepository
import com.insertu.instagramclonekotlin.login.data.LoginRepository
import com.insertu.instagramclonekotlin.profile.data.ProfileFakeDataSource
import com.insertu.instagramclonekotlin.profile.data.ProfileRepository
import com.insertu.instagramclonekotlin.splash.data.FakeLocalDataSource
import com.insertu.instagramclonekotlin.splash.data.SplashDataRepository

object DependencyInjector {

    fun SplashRepository() : SplashDataRepository {
        return SplashDataRepository(FakeLocalDataSource())
    }

    fun loginRepository() : LoginRepository{
        return LoginRepository(FakeDataRepository())
    }

    fun registerEmailRepository() : RegisterRepository{
        return RegisterRepository(FakeRegisterDataSource())
    }

    fun profileRepository() : ProfileRepository{
        return ProfileRepository(ProfileFakeDataSource())
    }

}