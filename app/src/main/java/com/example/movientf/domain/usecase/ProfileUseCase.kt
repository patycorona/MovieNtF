package com.example.movientf.domain.usecase

import com.example.movientf.data.model.request.ProfileRequest
import com.example.movientf.data.repository.ProfileRepository
import com.example.movientf.domain.model.ResultModel
import io.reactivex.Single
import javax.inject.Inject

class ProfileUseCase @Inject constructor(
    var profileRepository : ProfileRepository
){
    fun addProfile(token:String, profileRequest: ProfileRequest, id_client:String): Single<ResultModel> =
        profileRepository.addProfile(token, profileRequest, id_client)
}