package com.example.myfavoritewebsites.mapping

import com.example.myfavoritewebsites.models.url.*

internal fun AddUrlRequest.toModel() =
    AddUrlModel(url = url)
