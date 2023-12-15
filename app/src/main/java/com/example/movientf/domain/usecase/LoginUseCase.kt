package com.example.movientf.domain.usecase

import com.example.movientf.data.model.request.LoginRequest
import com.example.movientf.data.model.request.SendEmailRequest
import com.example.movientf.data.repository.LoginRepository
import com.example.movientf.domain.model.LoginResultModel
import com.example.movientf.domain.model.ResultModel
import io.reactivex.Single
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    var loginRepository :LoginRepository
) {
    fun login(loginRequest: LoginRequest): Single<LoginResultModel> =
        loginRepository.login(loginRequest)

    fun sendEmail(sendEmailRequest: SendEmailRequest): Single<ResultModel> =
        loginRepository.sendEmail(sendEmailRequest)

}
