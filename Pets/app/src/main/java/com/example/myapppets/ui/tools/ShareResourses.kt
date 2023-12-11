package com.example.myapppets.ui.tools

import android.content.Intent

class ShareResourses {

    fun shareTextPlain(title: String, value: String): Intent {
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, "Share PETS, $title")
            type = value
        }
        return sendIntent
    }

}



