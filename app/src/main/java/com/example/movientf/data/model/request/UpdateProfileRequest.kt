package com.example.movientf.data.model.request

import com.google.gson.annotations.SerializedName

data class UpdateProfileRequest(
    @SerializedName("id") val id_profile: String = "",
    @SerializedName("user_name") val user_name: String = "",
    @SerializedName("image") val image: String = "",
)
