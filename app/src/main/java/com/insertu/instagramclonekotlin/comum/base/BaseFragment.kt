package com.insertu.instagramclonekotlin.comum.base

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.insertu.instagramclonekotlin.R
import com.insertu.instagramclonekotlin.databinding.FragmentProfileBinding
import com.insertu.instagramclonekotlin.profile.view.ProfileFragment

abstract class BaseFragment<B, P : BasePresenter>(
    layoutId: Int,
    val bind: (View) -> B
) : Fragment(layoutId) {

    protected var binding: B? = null
    abstract var presenter: P

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = bind(view)
        setupView()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        getMenu()?.let {
            setHasOptionsMenu(true)
        }

        setupPresenter()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {

        getMenu()?.let {
            inflater.inflate(it, menu)
        }

        super.onCreateOptionsMenu(menu, inflater)
    }

    open fun getMenu(): Int?{
        return null
    }

    abstract fun setupPresenter()
    abstract fun setupView()
}