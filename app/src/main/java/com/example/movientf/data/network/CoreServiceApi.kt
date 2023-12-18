package com.example.movientf.data.network

import com.example.movientf.data.model.request.LoginRequest
import com.example.movientf.data.model.request.ProfileRequest
import com.example.movientf.data.model.request.SendEmailRequest
import com.example.movientf.data.model.response.LoginResponse
import com.example.movientf.data.model.response.ResultResponse
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path

interface CoreServiceApi {

    @POST("/auth/login")
    @Headers("Content-Type: application/json ")
    fun login(@Body loginRequest: LoginRequest): Single<LoginResponse>
    @POST("/auth/send-email")
    @Headers("Content-Type: application/json ")
    fun sendEmail(@Body sendEmailRequest: SendEmailRequest): Single<ResultResponse>
    @POST("/profile_method/{id_client}")
    @Headers("Content-Type: application/json ")
    fun addProfile(@Header("Authorization") authorization:String,
                   @Path("id_client") id_client:String,
                   @Body profileRequest: ProfileRequest): Single<ResultResponse>
    @GET("/profile_method/{id_client}")
    @Headers("Content-Type: application/json ")
    fun getProfile(@Header("Authorization") authorization:String,
                   @Path ("id_client") id_client:String): Single<ResultResponse>
}