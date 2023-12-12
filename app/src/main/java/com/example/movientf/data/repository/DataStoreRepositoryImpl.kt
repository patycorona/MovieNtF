package com.example.movientf.data.repository

import com.example.movientf.AndroidApplication.Companion.appContext
import com.example.movientf.data.database.readString
import com.example.movientf.data.database.writeString

class DataStoreRepositoryImpl: DataStoreRepository {

    companion object {
        const val NAME_USER_KEY = "NAME_USER_KEY"
        const val SHARED_TOKEN = "TOKEN"
    }

    override suspend fun setToken(token: String) {
        appContext.writeString(SHARED_TOKEN, token)
    }

    override fun getToken() = appContext.readString(SHARED_TOKEN)

    override suspend fun setName(name: String) {
        appContext.writeString(NAME_USER_KEY, name)
    }

    override fun getName() = appContext.readString(NAME_USER_KEY)

}