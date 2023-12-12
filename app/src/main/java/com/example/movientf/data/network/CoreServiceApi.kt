package com.example.movientf.data.network

import com.example.movientf.data.model.request.LoginRequest
import com.example.movientf.data.model.response.LoginResponse
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface CoreServiceApi {

    @POST("/auth/login")
    @Headers("Content-Type: application/json ")
    fun login(@Body loginRequest: LoginRequest): Single<LoginResponse>
}