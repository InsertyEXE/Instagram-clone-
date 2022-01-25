package com.insertu.instagramclonekotlin.cadastro.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.insertu.instagramclonekotlin.R
import com.insertu.instagramclonekotlin.cadastro.CadastroView
import com.insertu.instagramclonekotlin.comum.util.TxtWatcher
import com.insertu.instagramclonekotlin.databinding.FragmentCadastroEmailBinding

class CadastrarEmailFragment : Fragment(R.layout.fragment_cadastro_email), CadastroView.View {

    private var binding: FragmentCadastroEmailBinding? = null


    override lateinit var presenter: CadastroView.Presenter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentCadastroEmailBinding.bind(view)

        binding?.let {

            with(it){
                cadastroTxtLogin.setOnClickListener {
                    activity?.finish()
                }


                cadastroEdtEmail.addTextChangedListener(watcher)
                cadastroEdtEmail.addTextChangedListener(TxtWatcher{

                    displayEmailError(null)
                })

                loginBtnEnter.setOnClickListener {

                    presenter.create(cadastroEdtEmail.text.toString())
                }

            }
        }
    }

    private val watcher = TxtWatcher {
        binding?.loginBtnEnter?.isEnabled  = binding?.cadastroEdtEmail?.text.toString().isNotEmpty()

    }

    override fun displayEmailError(emailError: Int?) {

    }


    override fun onDestroy() {

        binding = null
        //presenter.onDestroy()

        super.onDestroy()

    }



}