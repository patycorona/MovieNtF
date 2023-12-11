package com.example.myapppets.ui.home.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.myapppets.R
import com.example.myapppets.databinding.ActivityMainBinding
import com.example.myapppets.domian.model.Screen
import com.example.myapppets.ui.register.views.UserRegisterFragment
import com.example.myapppets.ui.login.views.LoginFragment
import com.example.myapppets.ui.petregister.views.PetRegisterFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        changeScreen(Screen.LoginFragment)
        initListener()
    }

    private fun changeFragment(fragment: Fragment) {
        val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
        ft.replace(R.id.Fragment_principal, fragment)
        ft.commit()
    }
    private fun initListener() {
        binding?.tvSalir?.setOnClickListener {
            finish()
        }
        binding?.btnAddPet?.setOnClickListener {
            openAddPetFragment()
        }
    }

     fun changeScreen(typeScreen: Screen) {
        when (typeScreen) {
            Screen.MainVisit ->{
                openHomeFragment()
                createControlVisible(false)
            }
            Screen.LoginFragment -> {
                openLoginFragment()
                createControlVisible(false)
            }
            Screen.HomeFragment -> {
                openHomeFragment()
                createControlVisible(true)
            }
            Screen.UserRegisterFragment -> {
                openUserRegisterFragment()
                createControlVisible(false)
            }
            Screen.PetRegisterFragment -> {
                openAddPetFragment()
                createControlVisible(false)
            }
        }
    }

    private fun createControlVisible(valor:  Boolean) {
        if(valor){
            binding?.btnAddPet?.visibility = View.VISIBLE
        }else{
            binding?.btnAddPet?.visibility = View.GONE
        }
    }

    private fun openHomeFragment() {
        changeFragment(HomeFragment.newInstance())
    }

    private fun openLoginFragment() {
        changeFragment(LoginFragment.newInstance())
    }

    private fun openUserRegisterFragment() {
        changeFragment(UserRegisterFragment.newInstance())
    }

    private fun openAddPetFragment() {
        changeFragment(PetRegisterFragment.newInstance())
    }

}