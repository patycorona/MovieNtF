package com.example.myapppets.domian.model
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class ResultModel(
    var code: String = "",
    var message: String = ""
) : Parcelable