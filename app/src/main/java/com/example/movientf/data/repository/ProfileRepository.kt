package com.example.movientf.data.repository

import com.example.movientf.data.model.request.ProfileRequest
import com.example.movientf.domain.model.ResultModel
import io.reactivex.Single

interface ProfileRepository {

    fun addProfile(profileRequest: ProfileRequest): Single<ResultModel>
}