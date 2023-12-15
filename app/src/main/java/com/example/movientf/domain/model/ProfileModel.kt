package com.example.movientf.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class ProfileModel (
    var id :String = "",
    var user_name :String = "",
    var image :String = "",
): Parcelable