package com.example.myfavoritewebsites.models.url

import com.google.gson.annotations.SerializedName

data class AddUrlRequest(
    @SerializedName("url") val url: String = ""
)
