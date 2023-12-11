package com.example.myapppets.data.repository

import com.example.myapppets.data.model.mapping.toModel
import com.example.myapppets.data.network.CoreHomeApi
import com.example.myapppets.domian.model.PetModel
import io.reactivex.Single
import javax.inject.Inject

class AllPetRepositoryImpl @Inject constructor(var apiService: CoreHomeApi) : AllPetRepository {

    override fun getAllPet():Single<MutableList<PetModel>> = apiService.getAllPet()
            .map { userAccessResponse ->
                userAccessResponse.toModel()
            }

}