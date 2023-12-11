package com.example.myapppets.domian.usecase

import com.example.myapppets.data.model.request.UserAccessRequest
import com.example.myapppets.data.repository.UserAccessRepository
import com.example.myapppets.domian.model.ResultModel
import io.reactivex.Single
import javax.inject.Inject

class UserAccessUseCase @Inject constructor(
    var userAccessRepository: UserAccessRepository
)  {
    fun userAccess(userAccessRequest: UserAccessRequest): Single<ResultModel> =
        userAccessRepository.userAccess(userAccessRequest)

}