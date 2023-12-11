package com.example.myapppets.data.model.response

import com.google.gson.annotations.SerializedName

data class PetResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String = "",
    @SerializedName("type") val type: String = "",
    @SerializedName("raza") val raza: String = "",
    @SerializedName("obs") val obs: String = "",
    @SerializedName("url_image") val image: String = ""
)