package com.example.myapppets.data.model.mapping

import com.example.myapppets.data.model.response.PetRegisterResult
import com.example.myapppets.domian.model.ResultModel

internal fun PetRegisterResult.toModel() =
    ResultModel(code = code, message = message)