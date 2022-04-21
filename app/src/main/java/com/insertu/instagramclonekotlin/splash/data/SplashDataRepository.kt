package com.insertu.instagramclonekotlin.splash.data

class SplashDataRepository(private val dataSource: SplashDataSource) {

    fun session(callback: SplashSessionCallback){
        dataSource.session(callback)
    }

}