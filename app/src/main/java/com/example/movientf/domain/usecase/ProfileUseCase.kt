package com.example.movientf.domain.usecase

import com.example.movientf.data.model.request.ProfileRequest
import com.example.movientf.data.repository.ProfileRepository
import com.example.movientf.domain.model.ResultModel
import io.reactivex.Single
import javax.inject.Inject

class ProfileUseCase @Inject constructor(
    var profileRepository : ProfileRepository
){
    fun addProfile(profileRequest: ProfileRequest): Single<ResultModel> =
        profileRepository.addProfile(profileRequest)
}