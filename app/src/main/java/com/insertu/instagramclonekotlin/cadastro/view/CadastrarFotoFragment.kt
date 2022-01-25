package com.insertu.instagramclonekotlin.cadastro.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.insertu.instagramclonekotlin.comum.view.CustomDialog
import com.insertu.instagramclonekotlin.R
import com.insertu.instagramclonekotlin.databinding.FragmentCadastroFotoBinding

class CadastrarFotoFragment : Fragment(R.layout.fragment_cadastro_foto) {

    private var binding: FragmentCadastroFotoBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentCadastroFotoBinding.bind(view)


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

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }
}