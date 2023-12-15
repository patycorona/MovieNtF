package com.example.movientf.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.movientf.R
import com.example.movientf.databinding.ActivityMainBinding
import com.example.movientf.domain.model.ConstantGeneral.Companion.USER_REG
import com.example.movientf.ui.component.Screen
import com.example.movientf.ui.login.views.LoginFragment
import com.example.movientf.ui.presentation.PresentationActivity
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


        var type = intent?.extras?.getString(USER_REG)!!
        changeScreen(type)
    }

    fun changeScreen(type :String) {

        binding.apply {
            when(type){
                Screen.LoginFragment.toString()-> {
                    openLoginFragment()
                }
                Screen.MainActivity.toString() -> {
                    openLoginFragment()
                }
                Screen.UserRegisterFragment.toString() -> {
                    openUserRegisterFragment()
                }
                Screen.PresentationActivity.toString() -> {
                    startActivity()
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

    private fun startActivity() {
        startActivity(Intent(this, PresentationActivity::class.java))
        finish()
    }
}