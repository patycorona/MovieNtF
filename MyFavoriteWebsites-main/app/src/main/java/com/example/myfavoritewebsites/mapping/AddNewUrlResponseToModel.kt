package com.example.myfavoritewebsites.mapping

import com.example.myfavoritewebsites.models.url.AddUrlResponse
import com.example.myfavoritewebsites.models.url.AddUrlResponseModel
import com.example.myfavoritewebsites.models.url.AddUrlResult

internal fun AddUrlResponse.toModel() =
    AddUrlResponseModel(alias = alias, links = AddUrlResult(short = links.short, self = links.self))
