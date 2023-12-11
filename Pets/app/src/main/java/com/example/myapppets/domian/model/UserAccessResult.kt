package com.example.myapppets.domian.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class UserAccessResult (
    var code: String,
    var message: String = "",
    var user: UserModel
): Parcelable