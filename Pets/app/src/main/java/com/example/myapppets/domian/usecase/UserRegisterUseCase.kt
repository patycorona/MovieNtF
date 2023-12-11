package com.example.myapppets.domian.usecase

import com.example.myapppets.data.model.request.UserRequest
import com.example.myapppets.data.repository.UserRegisterRepository
import com.example.myapppets.domian.model.ResultModel
import io.reactivex.Single
import javax.inject.Inject

class UserRegisterUseCase @Inject constructor(
    var userRegisterRepository: UserRegisterRepository
) {
    fun userRegister(userRequest: UserRequest): Single<ResultModel> =
        userRegisterRepository.userRegister(userRequest)
}