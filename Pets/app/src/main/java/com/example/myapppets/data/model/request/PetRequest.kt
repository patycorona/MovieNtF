package com.example.myapppets.data.model.request

import com.google.gson.annotations.SerializedName

data class PetRequest (
    @SerializedName("name") val name: String = "",
    @SerializedName("type") val type: String = "",
    @SerializedName("raza") val raza: String = "",
    @SerializedName("obs") val obs: String = "",
    @SerializedName("image") val image: String = ""
        )