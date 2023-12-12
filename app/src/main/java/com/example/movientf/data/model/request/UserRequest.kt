package com.example.movientf.data.model.request

import com.google.gson.annotations.SerializedName

data class UserRequest(
    @SerializedName("email") val email: String = "",
    @SerializedName("name") val name: String = "",
    @SerializedName("last_name") val last_name: String = "",
    @SerializedName("password") val password: String = "",
    @SerializedName("birthday") val birthday: String = "",
    @SerializedName("address") val address: String = "",
    @SerializedName("phone") val phone: String = ""
)
