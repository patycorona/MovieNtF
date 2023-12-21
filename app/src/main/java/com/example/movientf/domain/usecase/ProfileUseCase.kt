package com.example.movientf.domain.usecase

import com.example.movientf.data.model.request.DeleteProfileRequest
import com.example.movientf.data.model.request.ProfileRequest
import com.example.movientf.data.model.request.UpdateProfileRequest
import com.example.movientf.data.repository.ProfileRepository
import com.example.movientf.domain.model.ProfileModel
import com.example.movientf.domain.model.ResultModel
import io.reactivex.Single
import javax.inject.Inject

class ProfileUseCase @Inject constructor(
    var profileRepository : ProfileRepository
){
    fun addProfile(token:String, id_client:String , profileRequest: ProfileRequest): Single<ResultModel> =
        profileRepository.addProfile(token, id_client, profileRequest)

    fun getProfile(token:String, id_client:String): Single<MutableList<ProfileModel>> =
        profileRepository.getProfile(token, id_client)

    fun deleteProfile(token:String, id_client:String, deleteProfileRequest: DeleteProfileRequest): Single<ResultModel> =
        profileRepository.deleteProfile(token, id_client, deleteProfileRequest)

    fun updateProfile(token:String, id_client:String, updateProfileRequest: UpdateProfileRequest) : Single<ResultModel> =
        profileRepository.updateProfile(token, id_client, updateProfileRequest)
}