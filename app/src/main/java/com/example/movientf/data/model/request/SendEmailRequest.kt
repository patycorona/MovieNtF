package com.example.movientf.data.model.request

import com.google.gson.annotations.SerializedName

data class SendEmailRequest(
    @SerializedName("email") val email: String = "",
)
