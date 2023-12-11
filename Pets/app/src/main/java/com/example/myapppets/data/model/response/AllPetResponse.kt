package com.example.myapppets.data.model.response

import com.google.gson.annotations.SerializedName

data class AllPetResponse(
    @SerializedName("list_pets")
    val list_pets: MutableList<PetResponse> = mutableListOf()
)


