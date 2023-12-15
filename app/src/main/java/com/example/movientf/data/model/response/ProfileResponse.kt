package com.example.movientf.data.model.response

import com.google.gson.annotations.SerializedName

data class ProfileResponse(
    @SerializedName("id") val id: String = "",
    @SerializedName("user_name") val user_name: String = "",
    @SerializedName("image") val image: String = "",
)
