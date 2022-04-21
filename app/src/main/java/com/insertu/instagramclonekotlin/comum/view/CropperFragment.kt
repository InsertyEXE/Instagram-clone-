package com.insertu.instagramclonekotlin.comum.view

import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import com.insertu.instagramclonekotlin.R
import com.insertu.instagramclonekotlin.databinding.FragmentImageCropperBinding
import java.io.File

class CropperFragment : Fragment(R.layout.fragment_image_cropper) {

    private var binding: FragmentImageCropperBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentImageCropperBinding.bind(view)

        val image = arguments?.getParcelable<Uri>(KEY_IMAGE)

        binding?.let {
            with(it) {

                cropperContainer.setAspectRatio(1, 1)
                cropperContainer.setFixedAspectRatio(true)
                cropperContainer.setImageUriAsync(image)

                cropperBtnCancel.setOnClickListener {
                    parentFragmentManager.popBackStack()
                }

                cropperContainer.setOnCropImageCompleteListener { view, result ->
                    Log.i("salvo", "caminho da imagem salva ${result.uri}")

                    setFragmentResult("cropkey", bundleOf(KEY_IMAGE to result.uri))
                    parentFragmentManager.popBackStack()
                }

                cropperBtnSave.setOnClickListener {

                    val dir = context?.getExternalFilesDir(Environment.DIRECTORY_PICTURES)


                    if (dir != null) {

                        val uriParaSalvar = Uri.fromFile(File(dir.path, System.currentTimeMillis().toString() + ".jpeg"))
                        cropperContainer.saveCroppedImageAsync(uriParaSalvar)

                    }


                }

            }
        }
    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }

    companion object {
        const val KEY_IMAGE = "key_image"
    }
}