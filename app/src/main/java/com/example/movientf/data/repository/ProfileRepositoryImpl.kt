package com.example.movientf.data.repository

import com.example.movientf.data.model.mapping.toModel
import com.example.movientf.data.model.request.DeleteProfileRequest
import com.example.movientf.data.model.request.ProfileRequest
import com.example.movientf.data.model.request.UpdateProfileRequest
import com.example.movientf.data.model.response.AllProfileResponse
import com.example.movientf.domain.model.ProfileModel
import com.example.movientf.domain.model.ResultModel
import io.reactivex.Single
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(var mockCoreServiceApi: MockCoreServiceApi, ) : ProfileRepository {

    override fun addProfile(token:String, id_client:String, profileRequest: ProfileRequest): Single<ResultModel> =
        mockCoreServiceApi.mockSendEmail()
            .map { resulResponse ->
                resulResponse.toModel()
            }

    override fun getProfile(token:String, id_client:String) : Single<MutableList<ProfileModel>> =
         mockCoreServiceApi.getProfilebyClient()
             .map{ allProfileResponse ->
                 allProfileResponse.toModel()
             }

    override fun deleteProfile(token:String, id_client:String, deleteProfileRequest: DeleteProfileRequest): Single<ResultModel> =
        mockCoreServiceApi.MockdeleteProfile()
            .map { resulResponse ->
                resulResponse.toModel()
            }

    override fun updateProfile(token:String, id_client:String, updateProfileRequest: UpdateProfileRequest) : Single<ResultModel> =
        mockCoreServiceApi.updateProfile()
            .map{ resulResponse ->
                resulResponse.toModel()
            }


}