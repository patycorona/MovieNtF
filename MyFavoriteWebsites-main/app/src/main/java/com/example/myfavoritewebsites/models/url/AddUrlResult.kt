package com.example.myfavoritewebsites.models.url

import com.google.gson.annotations.SerializedName

data class AddUrlResult(
    @SerializedName("short") val short: String = "",
    @SerializedName("self") val self: String = ""

)
