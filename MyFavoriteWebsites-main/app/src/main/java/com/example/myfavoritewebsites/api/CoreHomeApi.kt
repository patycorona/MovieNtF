package com.example.myfavoritewebsites.api

import com.example.myfavoritewebsites.models.url.AddUrlRequest
import com.example.myfavoritewebsites.models.url.AddUrlResponse
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface CoreHomeApi {

    @POST("/api/alias")
    @Headers("Content-Type: application/json ")
    fun addNewUrl(@Body addUrlRequest: AddUrlRequest): Single<AddUrlResponse>

    @GET("/api/alias/:id")
    @Headers("Content-Type: application/json ")
    fun getUrlRecently(): Single<AddUrlRequest>
}
