package com.example.myapppets.data.model.mapping

import com.example.myapppets.data.model.response.UserAccessResponse
import com.example.myapppets.domian.model.ResultModel

internal fun UserAccessResponse.toModel() =
    ResultModel(code = code, message = message)