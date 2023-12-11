package com.example.myfavoritewebsites.mapping

import com.example.myfavoritewebsites.models.url.AddUrlModel
import com.example.myfavoritewebsites.models.url.AddUrlResently

internal fun AddUrlResently.toModel(): MutableList<AddUrlModel> {
    val ListUrlResently: MutableList<AddUrlModel> = mutableListOf()

    urls.map { RB ->
        ListUrlResently.add(
            AddUrlModel(
                url = RB.url
            )
        )
    }

    return ListUrlResently
}
