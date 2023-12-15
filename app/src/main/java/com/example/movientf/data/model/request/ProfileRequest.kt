package com.example.movientf.data.model.request

import com.google.gson.annotations.SerializedName

data class ProfileRequest(
    @SerializedName("user_name") val user_name: String = "",
    @SerializedName("image") val image: String = "",
)
