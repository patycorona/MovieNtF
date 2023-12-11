package com.example.myapppets.data.model.request

import com.google.gson.annotations.SerializedName

data class UserRequest(
    @SerializedName("name") val name: String = "",
    @SerializedName("last_name") val lastName: String = "",
    @SerializedName("email") val email: String = "",
    @SerializedName("password") val password: String = ""
)
