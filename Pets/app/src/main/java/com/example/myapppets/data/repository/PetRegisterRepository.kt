package com.example.myapppets.data.repository

import com.example.myapppets.data.model.request.PetRequest
import com.example.myapppets.data.model.request.UserRequest
import com.example.myapppets.data.model.response.PetRegisterResult
import com.example.myapppets.domian.model.ResultModel
import io.reactivex.Single

interface PetRegisterRepository {
    fun petRegister (petRequest: PetRequest): Single<ResultModel>
}