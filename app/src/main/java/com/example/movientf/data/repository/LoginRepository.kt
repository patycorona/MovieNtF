package com.example.movientf.data.repository

import com.example.movientf.data.model.request.LoginRequest
import com.example.movientf.data.model.request.SendEmailRequest
import com.example.movientf.domain.model.LoginResultModel
import com.example.movientf.domain.model.ResultModel
import io.reactivex.Single

interface LoginRepository {
    fun login(loginRequest: LoginRequest): Single<LoginResultModel>

    fun sendEmail(sendEmailRequest: SendEmailRequest): Single<ResultModel>
}