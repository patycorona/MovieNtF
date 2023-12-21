package com.example.movientf.data.model.response

import com.google.gson.annotations.SerializedName

data class AllProfileResponse(
    @SerializedName("list_profile")
    val list_profile: MutableList<ProfileResponse> = mutableListOf()
)
