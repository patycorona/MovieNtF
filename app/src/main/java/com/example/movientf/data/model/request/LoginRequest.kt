package com.example.movientf.data.model.request

import com.google.gson.annotations.SerializedName

data class LoginRequest(
    @SerializedName("key") val email: String = "",
    @SerializedName("passw") val password: String = ""
)
