package com.example.movientf.data.repository

import com.example.movientf.data.model.request.DeleteProfileRequest
import com.example.movientf.data.model.request.ProfileRequest
import com.example.movientf.data.model.request.UpdateProfileRequest
import com.example.movientf.domain.model.ProfileModel
import com.example.movientf.domain.model.ResultModel
import io.reactivex.Single

interface ProfileRepository {

    fun addProfile(token:String, id_client:String, profileRequest: ProfileRequest): Single<ResultModel>

    fun getProfile(token:String, id_client:String): Single<MutableList<ProfileModel>>

    fun deleteProfile(token:String, id_client:String, deleteProfileRequest: DeleteProfileRequest): Single<ResultModel>

    fun updateProfile(token:String, id_client:String, updateProfileRequest: UpdateProfileRequest) : Single<ResultModel>
}