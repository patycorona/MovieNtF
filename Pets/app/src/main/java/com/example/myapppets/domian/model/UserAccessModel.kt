package com.example.myapppets.domian.model
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class UserAccessModel(
    var email: String = "",
    var password: String = ""
): Parcelable {}