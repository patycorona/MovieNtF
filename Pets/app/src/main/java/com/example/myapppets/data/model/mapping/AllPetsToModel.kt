package com.example.myapppets.data.model.mapping

import com.example.myapppets.data.model.response.AllPetResponse
import com.example.myapppets.domian.model.PetModel

internal fun AllPetResponse.toModel(): MutableList<PetModel> {
    val petsList: MutableList<PetModel> = mutableListOf()

    list_pets.map { PL ->
        petsList.add(
            PetModel(
                id = PL.id,
                name = PL.name,
                type = PL.type,
                raza = PL.raza,
                obs = PL.obs,
                image = PL.image
            )
        )
    }
    return petsList
}