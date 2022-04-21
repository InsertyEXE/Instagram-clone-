package com.insertu.instagramclonekotlin.cadastro.view

interface FragmentAttachListener {
    fun goToNameAndPassword(email: String)
    fun goToWelcome(name: String)
    fun goToPhoto()
    fun goToMainScreen()
    fun goToCropper()
    fun goToCameraScreen()
}