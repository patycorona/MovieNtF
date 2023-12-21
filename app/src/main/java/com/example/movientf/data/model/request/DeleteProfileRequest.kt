package com.example.movientf.data.model.request

import com.google.gson.annotations.SerializedName

data class DeleteProfileRequest(
    @SerializedName("id_profile") val id_profile: String = ""
)
