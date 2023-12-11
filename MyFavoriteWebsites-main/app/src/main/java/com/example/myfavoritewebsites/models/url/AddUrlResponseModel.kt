package com.example.myfavoritewebsites.models.url

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize

class AddUrlResponseModel(
    var alias: String = "",
    var links: @RawValue AddUrlResult = AddUrlResult()
) : Parcelable
