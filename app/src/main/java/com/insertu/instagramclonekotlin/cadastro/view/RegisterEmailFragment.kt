package com.insertu.instagramclonekotlin.cadastro.view

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.insertu.instagramclonekotlin.R
import com.insertu.instagramclonekotlin.cadastro.RegisterView
import com.insertu.instagramclonekotlin.cadastro.presenter.RegisterEmailPresenter
import com.insertu.instagramclonekotlin.comum.base.DependencyInjector
import com.insertu.instagramclonekotlin.comum.util.TxtWatcher
import com.insertu.instagramclonekotlin.databinding.FragmentCadastroEmailBinding

class RegisterEmailFragment : Fragment(R.layout.fragment_cadastro_email), RegisterView.View {

    private var binding: FragmentCadastroEmailBinding? = null
    private var fragmentAttachListener: FragmentAttachListener? = null


    override lateinit var presenter: RegisterView.Presenter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentCadastroEmailBinding.bind(view)


        val repository = DependencyInjector.registerEmailRepository()
        presenter = RegisterEmailPresenter(this, repository)
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

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is FragmentAttachListener){
            fragmentAttachListener = context
        }
    }

    private val watcher = TxtWatcher {
        binding?.loginBtnEnter?.isEnabled  = binding?.cadastroEdtEmail?.text.toString().isNotEmpty()

    }

    override fun showProgressBar(enable: Boolean) {
        binding?.loginBtnEnter?.showProgressbar(enable)
    }

    override fun displayEmailError(emailError: Int?) {
        binding?.cadastroEdtEmailInput?.error = emailError?.let { getString(it) }
    }

    override fun onEmailFailure(message: String) {
        binding?.cadastroEdtEmailInput?.error = message
    }

    override fun goToNameAndpassword(email: String) {
        fragmentAttachListener?.goToNameAndPassword(email)
    }


    override fun onDestroy() {

        binding = null
        fragmentAttachListener = null
        presenter.onDestroy()

        super.onDestroy()

    }



}