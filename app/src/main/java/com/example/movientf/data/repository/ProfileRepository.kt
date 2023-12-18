package com.example.movientf.data.repository

import com.example.movientf.data.model.request.ProfileRequest
import com.example.movientf.domain.model.ResultModel
import io.reactivex.Single

interface ProfileRepository {

    fun addProfile(token:String, profileRequest: ProfileRequest, id_client:String): Single<ResultModel>
}