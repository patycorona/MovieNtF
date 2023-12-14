package com.example.movientf.data.model.mapping

import com.example.movientf.data.model.response.LoginResponse
import com.example.movientf.data.model.response.ResultResponse
import com.example.movientf.domain.model.LoginResultModel
import com.example.movientf.domain.model.ResultModel
import com.example.movientf.domain.model.UserModel

internal fun LoginResponse.toModel() =
    LoginResultModel(code = code,
        message = message,
        user = UserModel(
            email = userRequest.email,
            name = userRequest.name,
            last_name = userRequest.last_name),
        token = token)

internal fun ResultResponse.toModel() =
    ResultModel(
        code = code,
        message = message,
    )