package com.insertu.instagramclonekotlin.main.view

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.WindowInsetsController
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.insertu.instagramclonekotlin.R
import com.insertu.instagramclonekotlin.camera.view.CameraFragment
import com.insertu.instagramclonekotlin.databinding.ActivityMainBinding
import com.insertu.instagramclonekotlin.extension.hiddenKeyboard
import com.insertu.instagramclonekotlin.extension.replaceFragment
import com.insertu.instagramclonekotlin.home.view.HomeFragment
import com.insertu.instagramclonekotlin.profile.view.ProfileFragment
import com.insertu.instagramclonekotlin.search.view.SearchFragment

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    private lateinit var binding: ActivityMainBinding

    private lateinit var homeFragment: HomeFragment
    private lateinit var searchFragment: SearchFragment
    private lateinit var profileFragment: ProfileFragment
    private lateinit var cameraFragment: CameraFragment

    private var currentFragment: Fragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.setSystemBarsAppearance(
                WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS,
                WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
            )
            window.statusBarColor = ContextCompat.getColor(this, R.color.gray)
        }

        setSupportActionBar(binding.toolbar)

        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_insta_camera)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = ""


        homeFragment = HomeFragment()
        profileFragment = ProfileFragment()
        searchFragment = SearchFragment()
        cameraFragment = CameraFragment()

        binding.menuBottomMain.setOnNavigationItemSelectedListener(this)
        binding.menuBottomMain.selectedItemId = R.id.menu_bottom_home

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menu_bottom_home ->{
                if (currentFragment == homeFragment) return false
                currentFragment = homeFragment
            }
            R.id.menu_bottom_add ->{
                if (currentFragment == cameraFragment) return false
                currentFragment = cameraFragment
            }
            R.id.menu_bottom_profile ->{
                if (currentFragment == profileFragment) return false
                currentFragment = profileFragment
            }
            R.id.menu_bottom_search ->{
                if (currentFragment == searchFragment) return false
                currentFragment = searchFragment
            }
        }


       currentFragment?.let {

          replaceFragment(R.id.main_fragment, it)

       }

        return true
    }
}