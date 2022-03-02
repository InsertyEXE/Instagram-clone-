package com.insertu.instagramclonekotlin.cadastro.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.ContentInfoCompat
import androidx.fragment.app.Fragment
import com.insertu.instagramclonekotlin.R
import com.insertu.instagramclonekotlin.cadastro.view.RegisterBemvindoFragment.Companion.KEY_NAME
import com.insertu.instagramclonekotlin.cadastro.view.RegisterSenhaFragment.Companion.KEY_EMAIL
import com.insertu.instagramclonekotlin.main.view.MainActivity


class RegisterActivity : AppCompatActivity(), FragmentAttachListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_cadastrar)

        val fragment = RegisterEmailFragment()
        replaceFragment(fragment)


    }

    override fun goToNameAndPassword(email: String) {

        val args = Bundle()
        args.putString(KEY_EMAIL, email)


        val registerPass = RegisterSenhaFragment()
        registerPass.arguments = args

        replaceFragment(registerPass)
    }

    override fun goToWelcome(name: String) {
        val args = Bundle()
        args.putString(KEY_NAME, name)

        val registerWelcome = RegisterBemvindoFragment()
        registerWelcome.arguments = args

        replaceFragment(registerWelcome)
    }

    override fun goToPhoto() {
        val fragmentPhoto = RegisterFotoFragment()
        replaceFragment(fragmentPhoto)
    }

    override fun goToMainScreen() {
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }


    private fun replaceFragment(fragment: Fragment){
        if(supportFragmentManager.findFragmentById(R.id.cadastro_fragment) == null){


            supportFragmentManager.beginTransaction().apply {
                add(R.id.cadastro_fragment, fragment)
                commit()
            }

        } else {


            supportFragmentManager.beginTransaction().apply {
                replace(R.id.cadastro_fragment, fragment)
                addToBackStack(null)
                commit()
            }

        }

    }


}