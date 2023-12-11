package com.example.myapppets.ui.component

import android.graphics.Bitmap
import android.util.Base64
import android.view.View
import java.io.ByteArrayOutputStream

fun View.setLayoutHeight(height: Float) {
    val layoutParams = this.layoutParams
    layoutParams.height = height.toInt()
    this.layoutParams = layoutParams
}

fun Bitmap.toByteArray(quality: Int): ByteArray {

    ByteArrayOutputStream().apply {
        compress(Bitmap.CompressFormat.JPEG, 10, this)
        return toByteArray()
    }
}

fun Bitmap.toBase64(): String? {
    return Base64.encodeToString(this.toByteArray(100), Base64.DEFAULT)
}