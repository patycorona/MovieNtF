package com.example.movientf.data.model.response

import com.google.gson.annotations.SerializedName

data class ResultResponse(
    @SerializedName("code") val code: String = "",
    @SerializedName("message") val message: String = ""
)
