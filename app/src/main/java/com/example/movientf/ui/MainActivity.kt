package com.example.movientf.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.movientf.R
import com.example.movientf.databinding.ActivityMainBinding
import com.example.movientf.ui.component.Screen
import com.example.movientf.ui.login.views.LoginFragment
import com.example.movientf.ui.register.UserRegisterFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initListener()
        changeScreen(Screen.LoginFragment)
    }

    private fun initListener() {

    }

    fun changeScreen(typeScreen: Screen) {

        binding.apply {
            when(typeScreen){
                Screen.LoginFragment -> {
                    openLoginFragment()
                }
                Screen.MainActivity -> {
                    initListener()
                }
                Screen.UserRegisterFragment -> {
                    openUserRegisterFragment()
                }
            }
        }

    }

    private fun changeFragment(fragment: Fragment) {
        val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
        ft.replace(R.id.Fragment_principal, fragment)
        ft.commit()
    }

    private fun openLoginFragment() {
        changeFragment(LoginFragment.newInstance())
    }

    private fun openUserRegisterFragment(){
        changeFragment(UserRegisterFragment.newInstance())
    }


}