package com.example.myapppets.data.repository

import com.example.myapppets.data.model.request.UserAccessRequest
import com.example.myapppets.domian.model.PetModel
import com.example.myapppets.domian.model.ResultModel
import io.reactivex.Single

interface AllPetRepository {
    fun getAllPet(): Single<MutableList<PetModel>>
}