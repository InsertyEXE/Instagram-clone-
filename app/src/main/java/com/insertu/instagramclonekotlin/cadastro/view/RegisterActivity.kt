package com.insertu.instagramclonekotlin.cadastro.view

import android.content.Intent
import android.media.AudioMetadata
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.FileProvider
import androidx.core.view.ContentInfoCompat
import androidx.fragment.app.Fragment
import com.insertu.instagramclonekotlin.R
import com.insertu.instagramclonekotlin.cadastro.view.RegisterBemvindoFragment.Companion.KEY_NAME
import com.insertu.instagramclonekotlin.cadastro.view.RegisterSenhaFragment.Companion.KEY_EMAIL
import com.insertu.instagramclonekotlin.comum.view.CropperFragment
import com.insertu.instagramclonekotlin.comum.view.CropperFragment.Companion.KEY_IMAGE
import com.insertu.instagramclonekotlin.extension.hiddenKeyboard
import com.insertu.instagramclonekotlin.extension.replaceFragment
import com.insertu.instagramclonekotlin.main.view.MainActivity
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.jvm.Throws


class RegisterActivity : AppCompatActivity(), FragmentAttachListener {

    private lateinit var currentFoto: Uri


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_cadastrar)

        val fragment = RegisterEmailFragment()
        replaceFragment(fragment)


    }

    override fun goToNameAndPassword(email: String) {

        val args = Bundle()
        args.putString(KEY_EMAIL, email)

        val registerPass = RegisterSenhaFragment()
        registerPass.arguments = args

        replaceFragment(registerPass)

    }

    override fun goToWelcome(name: String) {

        val args = Bundle()
        args.putString(KEY_NAME, name)

        val registerWelcome = RegisterBemvindoFragment()
        registerWelcome.arguments = args

        replaceFragment(registerWelcome)

    }

    override fun goToPhoto() {
        val fragmentPhoto = RegisterFotoFragment()
        replaceFragment(fragmentPhoto)
    }

    override fun goToMainScreen() {
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }


    private val contract =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->

            uri?.let {
                openIMageCropper(it)
            }

        }

    override fun goToCropper() {
        contract.launch("image/*")
    }


    private fun openIMageCropper(uri: Uri) {

        val fragment = CropperFragment().apply {
            arguments = Bundle().apply {
                putParcelable(KEY_IMAGE, uri)
            }
        }

        replaceFragment(fragment)

    }

    private val camera = registerForActivityResult(ActivityResultContracts.TakePicture()) { saved ->
        if (saved) {

            openIMageCropper(currentFoto)
        }
    }

    override fun goToCameraScreen() {

        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)


        if (cameraIntent.resolveActivity(packageManager) != null) {


            val fotoFile: File? = try {
                createImageFile()
            } catch (e: IOException) {
                Log.e("RegisterActivity", e.message, e)
                null
            }

            fotoFile?.also {


                val fotoUri = FileProvider.getUriForFile(this, "com.insertu.instagramclonekotlin.fileprovider", it)
                currentFoto = fotoUri

                camera.launch(fotoUri)
            }

        }
    }

    @Throws(IOException::class)
    private fun createImageFile(): File {
        val timestamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
        val dir = getExternalFilesDir(Environment.DIRECTORY_PICTURES)

        return File.createTempFile("JPEG_${timestamp}_", ".jpg", dir)
    }


    private fun replaceFragment(fragment: Fragment) {
        replaceFragment(R.id.cadastro_fragment, fragment)
        hiddenKeyboard()
    }


}