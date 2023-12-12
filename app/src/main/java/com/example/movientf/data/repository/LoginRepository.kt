package com.example.movientf.data.repository

import com.example.movientf.data.model.request.LoginRequest
import com.example.movientf.domain.model.LoginResultModel
import io.reactivex.Single

interface LoginRepository {
    fun login(loginRequest: LoginRequest): Single<LoginResultModel>
}