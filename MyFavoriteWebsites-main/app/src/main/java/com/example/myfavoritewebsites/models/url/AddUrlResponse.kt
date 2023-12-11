package com.example.myfavoritewebsites.models.url

import com.google.gson.annotations.SerializedName

data class AddUrlResponse(
    @SerializedName("alias") val alias: String = "",
    @SerializedName("_links") val links: AddUrlResult = AddUrlResult()
)
