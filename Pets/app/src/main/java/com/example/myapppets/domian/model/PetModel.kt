package com.example.myapppets.domian.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class PetModel (
    val id: Int,
    val name: String = "",
    val type: String = "",
    val raza: String = "",
    val obs: String = "",
    val image: String = ""
): Parcelable