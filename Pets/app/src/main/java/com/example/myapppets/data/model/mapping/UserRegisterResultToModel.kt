package com.example.myapppets.data.model.mapping

import com.example.myapppets.domian.model.ResultModel
import com.example.myapppets.data.model.response.UserResult

internal fun UserResult.toModel() =
    ResultModel(code = code, message = message)