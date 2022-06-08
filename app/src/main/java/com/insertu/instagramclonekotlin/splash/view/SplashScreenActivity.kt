package com.insertu.instagramclonekotlin.splash.view

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.insertu.instagramclonekotlin.R
import com.insertu.instagramclonekotlin.comum.base.DependencyInjector
import com.insertu.instagramclonekotlin.databinding.ActivitySplashScreenBinding
import com.insertu.instagramclonekotlin.extension.animateEnd
import com.insertu.instagramclonekotlin.login.view.LoginActivity
import com.insertu.instagramclonekotlin.main.view.MainActivity
import com.insertu.instagramclonekotlin.splash.Splash
import com.insertu.instagramclonekotlin.splash.presenter.SplashPresenter

class SplashScreenActivity : AppCompatActivity(), Splash.View {

    private lateinit var binding: ActivitySplashScreenBinding
    override lateinit var presenter: Splash.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val repository = DependencyInjector.SplashRepository()
        presenter = SplashPresenter(this, repository)

        binding.imgSplash.animate().apply {

            setListener(animateEnd{
                presenter.autheticated()
            })

            duration = 1000
            alpha(1.0f)
            start()
        }

    }


    override fun goToMainScreen() {
        binding.imgSplash.animate().apply {

            setListener(animateEnd {
                val intent = Intent(baseContext, MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intent)
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            })

            duration = 1000
            startDelay = 1000
            alpha(0.0f)
            start()
        }

    }


    override fun goToLoginScreen() {
        binding.imgSplash.animate().apply {
            setListener(animateEnd {
                val intent = Intent(baseContext, LoginActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intent)
                overridePendingTransition(0, android.R.anim.fade_out)
            })

            duration = 1000
            startDelay = 1000
            alpha(0.0f)
            start()
        }

    }


    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }
}