package com.example.myfavoritewebsites.repository

import com.example.myfavoritewebsites.api.CoreHomeApi
import com.example.myfavoritewebsites.mapping.toModel
import com.example.myfavoritewebsites.models.url.AddUrlModel
import io.reactivex.Single
import javax.inject.Inject

class UrlRepository @Inject constructor(private val apiService: CoreHomeApi) {

    fun getRecentlyUrls(): Single<AddUrlModel> {
        return apiService.getUrlRecently()
            .map { urlR ->
                urlR.toModel()
            }
    }
}
