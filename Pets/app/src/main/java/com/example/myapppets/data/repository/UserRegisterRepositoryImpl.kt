package com.example.myapppets.data.repository

import com.example.myapppets.data.model.mapping.toModel
import com.example.myapppets.data.model.request.UserRequest
import com.example.myapppets.data.network.CoreHomeApi
import com.example.myapppets.domian.model.ResultModel
import io.reactivex.Single
import javax.inject.Inject

class UserRegisterRepositoryImpl  @Inject constructor(var apiService: CoreHomeApi) :UserRegisterRepository {
    override fun userRegister(userRequest: UserRequest): Single<ResultModel> =
        apiService.userRegister(userRequest)

            .map { userResult ->
                userResult.toModel()
            }
}