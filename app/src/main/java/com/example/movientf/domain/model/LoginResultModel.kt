package com.example.movientf.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class LoginResultModel (
    var code: String = "",
    var message: String = "",
    var user: UserModel = UserModel(),
    var token: String = ""
): Parcelable