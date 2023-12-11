package com.example.myapppets.domian.usecase

import com.example.myapppets.data.repository.AllPetRepository
import com.example.myapppets.domian.model.PetModel
import io.reactivex.Single
import javax.inject.Inject

class AllPetUseCase @Inject constructor(
    var allPetRepository: AllPetRepository
) {

    fun getAllPet():Single<MutableList<PetModel>> = allPetRepository.getAllPet()
}