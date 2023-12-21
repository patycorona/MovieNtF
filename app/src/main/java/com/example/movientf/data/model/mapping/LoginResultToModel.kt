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
            id_client = userResponse.id_client,
            email = userResponse.email,
            name = userResponse.name,
            last_name = userResponse.last_name),
        token = token)

internal fun ResultResponse.toModel() =
    ResultModel(
        code = code,
        message = message,
    )