package com.example.myapppets.data.repository

import com.example.myapppets.data.model.mapping.toModel
import com.example.myapppets.data.model.request.UserAccessRequest
import com.example.myapppets.domian.model.ResultModel
import com.example.myapppets.data.network.CoreHomeApi
import io.reactivex.Single
import javax.inject.Inject

class UserAccessRepositoryImpl @Inject constructor(var apiService: CoreHomeApi) : UserAccessRepository {

    override fun userAccess(userAccessRequest: UserAccessRequest): Single<ResultModel> =
        apiService.userAccess(userAccessRequest)

            .map { userAccessResponse ->
                userAccessResponse.toModel()
            }


}