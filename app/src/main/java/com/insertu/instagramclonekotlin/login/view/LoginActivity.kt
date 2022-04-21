package com.insertu.instagramclonekotlin.login.view


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.insertu.instagramclonekotlin.cadastro.view.RegisterActivity
import com.insertu.instagramclonekotlin.comum.base.DependencyInjector
import com.insertu.instagramclonekotlin.comum.util.TxtWatcher
import com.insertu.instagramclonekotlin.databinding.ActivityLoginBinding
import com.insertu.instagramclonekotlin.login.Login
import com.insertu.instagramclonekotlin.login.presenter.LoginPresenter
import com.insertu.instagramclonekotlin.main.view.MainActivity

class LoginActivity : AppCompatActivity(), Login.View {

    private lateinit var binding: ActivityLoginBinding

    override lateinit var presenter: Login.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)


        presenter = LoginPresenter(this, DependencyInjector.loginRepository())

        binding.loginEdtEmail.addTextChangedListener(watcher)
        binding.loginEdtSenha.addTextChangedListener(watcher)

        with(binding) {

            loginEdtEmail.addTextChangedListener(watcher)
            loginEdtEmail.addTextChangedListener(TxtWatcher{
                displayEmailFailure(null)
            })


            loginEdtSenha.addTextChangedListener(watcher)
            loginEdtSenha.addTextChangedListener(TxtWatcher{
                displayPasswordFailure(null)
            })

            loginBtnEnter.setOnClickListener {
                presenter.login(loginEdtEmail.text.toString(), loginEdtSenha.text.toString())

            }


            loginTxtRegister.setOnClickListener {
                goToRegister()
            }


        }

    }

    private val watcher = TxtWatcher {
        binding.loginBtnEnter.isEnabled = binding.loginEdtEmail.text.toString().isNotEmpty()
                && binding.loginEdtSenha.text.toString().isNotEmpty()
    }

    private fun goToRegister(){
        startActivity(Intent(this, RegisterActivity::class.java))
    }

    override fun showProgressBar(enabled: Boolean) {
        binding.loginBtnEnter.showProgressbar(enabled)
    }

    override fun displayEmailFailure(emailError: Int?) {
        binding.loginEdtEmailInput.error = emailError?.let { getString(it) }
    }

    override fun displayPasswordFailure(passwordError: Int?) {
        binding.loginEdtSenhaInput.error = passwordError?.let { getString(it) }
    }

    override fun authLoginSucessful() {
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)

    }

    override fun authLoginFailure(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

}