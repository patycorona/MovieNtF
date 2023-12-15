package com.example.movientf.data.repository

import com.example.movientf.data.model.mapping.toModel
import com.example.movientf.data.model.request.ProfileRequest
import com.example.movientf.domain.model.ResultModel
import io.reactivex.Single
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(var mockCoreServiceApi: MockCoreServiceApi, ) : ProfileRepository {

    override fun addProfile(profileRequest: ProfileRequest): Single<ResultModel> =
        mockCoreServiceApi.mockSendEmail()

            .map { resulResponse ->
                resulResponse.toModel()
            }
}