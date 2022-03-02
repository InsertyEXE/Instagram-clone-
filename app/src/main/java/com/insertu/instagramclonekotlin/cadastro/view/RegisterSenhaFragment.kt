package com.insertu.instagramclonekotlin.cadastro.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.insertu.instagramclonekotlin.R
import com.insertu.instagramclonekotlin.cadastro.RegisterNameAndPassword
import com.insertu.instagramclonekotlin.cadastro.RegisterView
import com.insertu.instagramclonekotlin.cadastro.presenter.RegisterEmailPresenter
import com.insertu.instagramclonekotlin.cadastro.presenter.RegisterSenhaPresenter
import com.insertu.instagramclonekotlin.comum.base.DependencyInjector
import com.insertu.instagramclonekotlin.comum.util.TxtWatcher
import com.insertu.instagramclonekotlin.databinding.FragmentCadastroSenhaBinding

class RegisterSenhaFragment : Fragment(R.layout.fragment_cadastro_senha),
    RegisterNameAndPassword.View {

    private var binding: FragmentCadastroSenhaBinding? = null
    private var fragmentAttachListener: FragmentAttachListener? = null
    override lateinit var presenter: RegisterNameAndPassword.Presenter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentCadastroSenhaBinding.bind(view)

        val repository = DependencyInjector.registerEmailRepository()
        presenter = RegisterSenhaPresenter(this, repository)

        val email = arguments?.getString(KEY_EMAIL) ?: throw IllegalArgumentException("Email não encontrado")


        binding?.let {
            with(it) {


                cadastroEdtSenha.addTextChangedListener(watcher)
                cadastroEdtNome.addTextChangedListener(watcher)
                cadastroEdtConfirme.addTextChangedListener(watcher)


                continueBtnEnter.setOnClickListener {

                    presenter.create(
                        email,
                        cadastroEdtNome.text.toString(),
                        cadastroEdtSenha.text.toString(),
                        cadastroEdtConfirme.text.toString()
                    )

                }


                cadastroEdtNome.addTextChangedListener(TxtWatcher {
                    displayNameError(null)
                })
                cadastroEdtSenha.addTextChangedListener(TxtWatcher {
                    displayPasswordError(null)
                })
                cadastroEdtConfirme.addTextChangedListener(TxtWatcher {
                    displayPasswordError(null)
                })

            }
        }


    }

    companion object {

        const val KEY_EMAIL = "key_email"

    }

    override fun onDestroy() {


        binding = null
        presenter.onDestroy()
        super.onDestroy()

    }


    override fun onAttach(context: Context) {

        super.onAttach(context)
        if(context is FragmentAttachListener){
            fragmentAttachListener = context
        }

    }


    private val watcher = TxtWatcher {
        binding?.continueBtnEnter?.isEnabled =
            binding?.cadastroEdtNome?.text.toString().isNotBlank()
                    && binding?.cadastroEdtSenha?.text.toString().isNotBlank()
                    && binding?.cadastroEdtConfirme?.text.toString().isNotBlank()
    }


    override fun showProgressBar(enable: Boolean) {
        binding?.continueBtnEnter?.showProgressbar(enable)
    }

    override fun displayNameError(nameError: Int?) {
        binding?.cadastroEdtNomeInput?.error = nameError?.let { getString(it) }
    }

    override fun displayPasswordError(passwordError: Int?) {
        binding?.cadastroEdtSenhaInput?.error = passwordError?.let { getString(it) }
    }

    override fun onCreateFailure(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

    override fun onCreateSucess(email: String, name: String) {
        fragmentAttachListener?.goToWelcome(name)
    }
}