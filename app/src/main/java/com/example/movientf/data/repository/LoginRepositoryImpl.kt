package com.example.movientf.data.repository

import com.example.movientf.data.model.mapping.toModel
import com.example.movientf.data.model.request.LoginRequest
import com.example.movientf.domain.model.LoginResultModel
import io.reactivex.Single
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(var mockCoreServiceApi: MockCoreServiceApi, ) :LoginRepository {

    override fun login(loginRequest: LoginRequest) : Single<LoginResultModel> =
        mockCoreServiceApi.mocklogin()

            .map { loginResponse ->
                loginResponse.toModel()
            }
}