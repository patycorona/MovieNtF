package com.example.myapppets.data.model.response

import com.example.myapppets.data.model.request.UserRequest
import com.google.gson.annotations.SerializedName

data class UserAccessResponse(
    @SerializedName("code") val code: String = "",
    @SerializedName("message") val message: String = "",
    @SerializedName("user") val user: UserRequest = UserRequest()
)
