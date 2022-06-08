package com.insertu.instagramclonekotlin.cadastro.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.insertu.instagramclonekotlin.R
import com.insertu.instagramclonekotlin.cadastro.RegisterNameAndPassword
import com.insertu.instagramclonekotlin.cadastro.presenter.RegisterSenhaPresenter
import com.insertu.instagramclonekotlin.comum.base.DependencyInjector
import com.insertu.instagramclonekotlin.comum.util.TxtWatcher
import com.insertu.instagramclonekotlin.databinding.FragmentCadastroSenhaBinding
import com.insertu.instagramclonekotlin.databinding.FragmentCadastroWelcomeBinding

class RegisterBemvindoFragment : Fragment(R.layout.fragment_cadastro_welcome) {

    private var binding: FragmentCadastroWelcomeBinding? = null
    private var fragmentAttachListener: FragmentAttachListener? = null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentCadastroWelcomeBinding.bind(view)

        val nome = arguments?.getString(KEY_NAME) ?: throw IllegalArgumentException("Nome não encontrado")


        binding?.let {
            with(it) {

                cadastroWelcomeBtnEnter.isEnabled = true

                cadastroWelcomeTxt.text = getString(R.string.welcome_to_instagram, nome)
                cadastroWelcomeBtnEnter.setOnClickListener {
                    fragmentAttachListener?.goToPhoto()
                }

            }
        }


    }

    companion object {
        const val KEY_NAME = "key_nome"
    }

    override fun onDestroy() {
        binding = null
        fragmentAttachListener = null
        super.onDestroy()
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is FragmentAttachListener){
            fragmentAttachListener = context
        }

    }
}