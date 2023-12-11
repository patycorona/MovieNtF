package com.example.myapppets.data.repository

import com.example.myapppets.data.model.request.UserRequest
import com.example.myapppets.domian.model.ResultModel
import io.reactivex.Single

interface UserRegisterRepository {
    fun userRegister(userRequest: UserRequest): Single<ResultModel>
}