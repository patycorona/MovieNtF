package com.example.movientf.data.model.mapping

import com.example.movientf.data.model.response.AllProfileResponse
import com.example.movientf.domain.model.ProfileModel

internal fun AllProfileResponse.toModel() : MutableList<ProfileModel> {
    val list: MutableList<ProfileModel> = mutableListOf()

    list_profile.map { PR ->
        list.add(
            ProfileModel(
                id = PR.id,
                user_name = PR.user_name,
                image = PR.image
            )
        )
    }



    return list
}





