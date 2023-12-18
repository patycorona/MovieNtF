package com.example.movientf.data.model.response

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("code") val code: String = "",
    @SerializedName("message") val message: String = "",
    @SerializedName("user") val userRequest: UserResponse = UserResponse(),
    @SerializedName("token") val token: String = ""
)
