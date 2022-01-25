package com.insertu.instagramclonekotlin.cadastro.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.insertu.instagramclonekotlin.R
import com.insertu.instagramclonekotlin.databinding.ActivityCadastrarBinding

class CadastroActivity : AppCompatActivity() {




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_cadastrar)

        val fragment = CadastrarEmailFragment()

        supportFragmentManager.beginTransaction().apply {
            add(R.id.cadastro_fragment, fragment)
            commit()
        }
    }
}