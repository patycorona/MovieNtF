package com.example.myapppets.data.model.response

import com.google.gson.annotations.SerializedName

data class PetRegisterResult(
    @SerializedName("code") val code: String = "",
    @SerializedName("message") val message: String = "",
)
