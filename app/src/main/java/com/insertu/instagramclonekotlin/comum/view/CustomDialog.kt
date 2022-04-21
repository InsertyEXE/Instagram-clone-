package com.insertu.instagramclonekotlin.comum.view

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.LinearLayout
import android.widget.TextView
import com.insertu.instagramclonekotlin.R
import com.insertu.instagramclonekotlin.databinding.ActivityCustomDialogBinding

class CustomDialog(context: Context) : Dialog(context) {

    private lateinit var txtButtons: Array<TextView>
    var titleId: Int? = null

    private lateinit var binding: ActivityCustomDialogBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCustomDialogBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

    override fun setTitle(titleId: Int) {
        this.titleId = titleId
    }

    //vararg é um array do kotlin, mais moderno
    fun addButton( vararg texts: Int, listener: View.OnClickListener) {

        //adicionando varios texts views
        txtButtons = Array(texts.size) {
            TextView(context)
        }

        //cofigurando os elementos da lista
        texts.forEachIndexed { index, texto ->
            txtButtons[index].id = texto
            txtButtons[index].setText(texto)
            txtButtons[index].setOnClickListener {
                listener.onClick(it)
            }
        }

    }


    override fun show() {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        super.show()

        //vendo se tem um titulo diferente
        titleId?.let {
            binding.dialogTitle.setText(it)
        }

        //fazendo loop da lista dinamica
        for (texto in txtButtons){

            //configurando o layout por linhas de codigo
            val layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
            layoutParams.setMargins(30, 50, 30, 50)

            binding.dialogLayout.addView(texto, layoutParams)
        }
    }


}