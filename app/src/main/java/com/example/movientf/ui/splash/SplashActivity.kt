package com.example.movientf.ui.splash

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.movientf.R
import com.example.movientf.databinding.ActivitySplashBinding
import com.example.movientf.domain.model.ConstantGeneral.Companion.URI_RESOURCE
import com.example.movientf.ui.MainActivity
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {

    lateinit var binding: ActivitySplashBinding
    private val SPLASH_VIDEO = "/" + R.raw.movie_ntf
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.hide()
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        startVideo()
    }

    private fun startVideo() {
        binding?.wvVideo?.setVideoURI(Uri.parse(URI_RESOURCE + packageName + SPLASH_VIDEO))
        binding?.wvVideo?.start()

        binding?.wvVideo?.setOnCompletionListener {
            startActivity()
        }
    }

    private fun startActivity() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}