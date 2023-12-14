package com.example.movientf.ui.presentation

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.addCallback
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.movientf.R
import com.example.movientf.databinding.ActivityPresentationBinding
import com.example.movientf.domain.model.ConstantGeneral.Companion.ONE
import com.example.movientf.domain.model.ConstantGeneral.Companion.THREE
import com.example.movientf.domain.model.ConstantGeneral.Companion.TWO
import com.example.movientf.domain.model.ConstantGeneral.Companion.USER_REG
import com.example.movientf.domain.model.ConstantGeneral.Companion.ZERO
import com.example.movientf.ui.MainActivity
import com.example.movientf.ui.component.Screen
import com.example.movientf.ui.presentation.views.adapter.ViewPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PresentationActivity : AppCompatActivity() {

    lateinit var binding: ActivityPresentationBinding
    private val adapter by lazy {ViewPagerAdapter(this)}

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val callback = this@PresentationActivity.onBackPressedDispatcher.addCallback(this) {
            finish()
        }

        binding = ActivityPresentationBinding.inflate(layoutInflater)
        supportActionBar?.hide()
        setContentView(binding.root)

        binding?.apply {
            pager.adapter = adapter
            val tabLayoutMediator = TabLayoutMediator(tabLayout, pager,
            TabLayoutMediator.TabConfigurationStrategy{ tab, position ->

                    when(position){
                        ZERO -> { tab.setIcon(R.drawable.ic_point_orange)}
                        ONE -> { tab.setIcon(R.drawable.ic_point_orange)}
                        TWO -> { tab.setIcon(R.drawable.ic_point_orange)}
                        THREE -> { tab.setIcon(R.drawable.ic_point_orange)}
                        else ->{
                            tab.setIcon(R.drawable.ic_point_gray)
                        }
                }
            })
            tabLayoutMediator.attach()

            tvIrLogin.setOnClickListener {
               startActivity()
            }
            tvPrivaciada.setOnClickListener {
                Toast.makeText(this@PresentationActivity, R.string.lbl_privacidad, Toast.LENGTH_SHORT).show()
            }

            btnComenzar.setOnClickListener {
                openFragment(Screen.UserRegisterFragment)
            }
        }
    }

    private fun openFragment( type : Screen){
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra(USER_REG,type)
        startActivity(intent)
    }

    private fun startActivity() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}