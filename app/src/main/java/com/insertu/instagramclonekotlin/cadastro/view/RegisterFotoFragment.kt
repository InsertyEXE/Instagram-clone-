package com.insertu.instagramclonekotlin.cadastro.view

import android.content.Context
import android.graphics.ImageDecoder
import android.media.Image
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import com.insertu.instagramclonekotlin.comum.view.CustomDialog
import com.insertu.instagramclonekotlin.R
import com.insertu.instagramclonekotlin.cadastro.RegisterPhoto
import com.insertu.instagramclonekotlin.cadastro.presenter.RegisterPhotoPresenter
import com.insertu.instagramclonekotlin.comum.base.DependencyInjector
import com.insertu.instagramclonekotlin.comum.view.CropperFragment
import com.insertu.instagramclonekotlin.databinding.FragmentCadastroFotoBinding

class RegisterFotoFragment : Fragment(R.layout.fragment_cadastro_foto), RegisterPhoto.View {

    private var binding: FragmentCadastroFotoBinding? = null
    private var fragmentAttachListener: FragmentAttachListener? = null
    override lateinit var presenter: RegisterPhoto.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setFragmentResultListener("cropkey") { requestKey, bundle ->

            val fotoCortadaUri = bundle.getParcelable<Uri>(CropperFragment.KEY_IMAGE)

            onCroppedImage(fotoCortadaUri)
        }
    }

    private fun onCroppedImage(fotoCortadaUri: Uri?) {

        if (fotoCortadaUri != null) {
            val bitmap = if (Build.VERSION.SDK_INT >= 28) {

                val source = ImageDecoder.createSource(requireContext().contentResolver, fotoCortadaUri)
                ImageDecoder.decodeBitmap(source)

            } else
                 MediaStore.Images.Media.getBitmap(requireContext().contentResolver, fotoCortadaUri)


            binding?.cadastroImgPerfil?.setImageBitmap(bitmap)
            presenter.update(fotoCortadaUri)
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentCadastroFotoBinding.bind(view)

        val repository = DependencyInjector.registerEmailRepository()
        presenter = RegisterPhotoPresenter(this, repository)

        binding?.let {
            with(it) {

                cadastroFotoBtnPular.setOnClickListener {
                    fragmentAttachListener?.goToMainScreen()
                }

                cadastroFotoBtnEscolher.isEnabled = true
                cadastroFotoBtnEscolher.setOnClickListener {

                    openGallery()

                }
            }
        }


    }

    private fun openGallery() {

        val customDialog = CustomDialog(requireContext())
        customDialog.setTitle(R.string.app_name)
        customDialog.addButton(R.string.photo, R.string.gallery) {
            when (it.id) {
                R.string.photo -> {
                    fragmentAttachListener?.goToCameraScreen()
                    customDialog.cancel()

                }

                R.string.gallery -> {
                    fragmentAttachListener?.goToCropper()
                    customDialog.cancel()
                }
            }
        }
        customDialog.show()

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is FragmentAttachListener)
            fragmentAttachListener = context
    }

    override fun onDestroy() {
        binding = null
        presenter.onDestroy()
        super.onDestroy()
    }

    override fun showProgressBar(enable: Boolean) {
        binding?.cadastroFotoBtnEscolher?.showProgressbar(enable)
    }

    override fun onUpdateFailure(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
    }

    override fun onUpdateSucess() {
        fragmentAttachListener?.goToMainScreen()
    }
}