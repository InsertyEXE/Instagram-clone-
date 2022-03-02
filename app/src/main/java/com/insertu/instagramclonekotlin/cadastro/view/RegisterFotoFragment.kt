package com.insertu.instagramclonekotlin.cadastro.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.insertu.instagramclonekotlin.comum.view.CustomDialog
import com.insertu.instagramclonekotlin.R
import com.insertu.instagramclonekotlin.databinding.FragmentCadastroFotoBinding

class RegisterFotoFragment : Fragment(R.layout.fragment_cadastro_foto) {

    private var binding: FragmentCadastroFotoBinding? = null
    private var fragmentAttachListener: FragmentAttachListener? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentCadastroFotoBinding.bind(view)

        binding?.let {
            with(it){

                cadastroFotoBtnPular.setOnClickListener {
                    fragmentAttachListener?.goToMainScreen()
                }

                cadastroFotoBtnEnter.isEnabled = true
                cadastroFotoBtnEnter.setOnClickListener {

                    val customDialog = CustomDialog(requireContext())
                    customDialog.setTitle(R.string.app_name)
                    customDialog.addButton(R.string.photo, R.string.gallery) {
                        when (it.id) {
                            R.string.photo -> {
                                Toast.makeText(requireContext(), "Foto", Toast.LENGTH_SHORT).show()

                            }

                            R.string.gallery -> {
                                Toast.makeText(requireContext(), "Galeria", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                    customDialog.show()

                }
            }
        }



    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is FragmentAttachListener)
            fragmentAttachListener = context
    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }
}