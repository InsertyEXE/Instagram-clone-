package com.insertu.instagramclonekotlin.splash.data

interface SplashDataSource {
    fun session(callback: SplashSessionCallback)
}