package com.example.myapppets.data.repository

import com.example.myapppets.data.model.mapping.toModel
import com.example.myapppets.data.model.request.PetRequest
import com.example.myapppets.data.model.request.UserRequest
import com.example.myapppets.data.model.response.PetRegisterResult
import com.example.myapppets.data.network.CoreHomeApi
import com.example.myapppets.domian.model.ResultModel
import io.reactivex.Single
import javax.inject.Inject

class PetRegisterRepositoryImpl @Inject constructor (var apiService: CoreHomeApi) : PetRegisterRepository{

    override fun petRegister(petRequest: PetRequest): Single<ResultModel> =
        apiService.petRegister(petRequest)
            .map { userResult ->
                userResult.toModel()
            }
}