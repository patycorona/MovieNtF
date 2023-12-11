package com.example.myfavoritewebsites.repository

import com.example.myfavoritewebsites.api.CoreHomeApi
import com.example.myfavoritewebsites.mapping.toModel
import com.example.myfavoritewebsites.models.url.AddUrlRequest
import com.example.myfavoritewebsites.models.url.AddUrlResponseModel
import io.reactivex.Single
import javax.inject.Inject

class AddUrlRepository @Inject constructor(
    private val apiService: CoreHomeApi
) {
    fun addNewUrl(url: String): Single<AddUrlResponseModel> {
        return apiService.addNewUrl(
            addUrlRequest = AddUrlRequest(
                url = url
            )
        )
            .map { addurlResponse ->
                addurlResponse.toModel()
            }
    }
}
