package com.example.myapppets.data.network

import com.example.myapppets.data.model.request.PetRequest
import com.example.myapppets.data.model.request.UserAccessRequest
import com.example.myapppets.data.model.request.UserRequest
import com.example.myapppets.data.model.response.AllPetResponse
import com.example.myapppets.data.model.response.PetRegisterResult
import com.example.myapppets.data.model.response.UserAccessResponse
import com.example.myapppets.data.model.response.UserResult
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface CoreHomeApi {

    @POST("/access_pet")
    @Headers("Content-Type: application/json ")
    fun userAccess(@Body userAccessRequest: UserAccessRequest): Single<UserAccessResponse>

    @POST("/register_user_pet")
    @Headers("Content-Type: application/json ")
    fun userRegister(@Body userRequest: UserRequest): Single<UserResult>

    @GET("/pets")
    @Headers("Content-Type: application/json ")
    fun getAllPet(): Single<AllPetResponse>

    @POST("/pet")
    @Headers("Content-Type: application/json ")
    fun petRegister(@Body petRequest: PetRequest): Single<PetRegisterResult>

}