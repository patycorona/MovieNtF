package com.example.myfavoritewebsites.models.url

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class AddUrlModel(
    var url: String = "",
) : Parcelable
