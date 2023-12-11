package com.example.myapppets.domian.model
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class UserModel(

    var email: String = "",
    var password: String = "",
    var name: String = "",
    var lastName: String = ""

) : Parcelable