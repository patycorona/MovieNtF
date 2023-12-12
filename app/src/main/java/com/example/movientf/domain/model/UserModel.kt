package com.example.movientf.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserModel(
    var email:String = "",
    var name:String = "",
    var last_name:String = "",
    var password:String = "",
    var birthday: String = "",
    var address: String = "",
    var phone: String = ""
): Parcelable