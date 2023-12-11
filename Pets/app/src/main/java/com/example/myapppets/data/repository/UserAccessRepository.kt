package com.example.myapppets.data.repository

import com.example.myapppets.data.model.request.UserAccessRequest
import com.example.myapppets.data.model.request.UserRequest
import com.example.myapppets.domian.model.ResultModel
import io.reactivex.Single

interface UserAccessRepository {
    fun userAccess(userAccessRequest: UserAccessRequest): Single<ResultModel>
}