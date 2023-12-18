package com.example.movientf.data.repository

import com.example.movientf.AndroidApplication.Companion.appContext
import com.example.movientf.data.database.readString
import com.example.movientf.data.database.writeString

class DataStoreRepositoryImpl: DataStoreRepository {

    companion object {
        const val ID_CLIENT = "ID_CLIENT"
        const val SHARED_TOKEN = "TOKEN"
    }

    override suspend fun setToken(token: String) {
        appContext.writeString(SHARED_TOKEN, token)
    }

    override fun getToken() = appContext.readString(SHARED_TOKEN)

    override suspend fun setIdClient(idClient: String) {
        appContext.writeString(ID_CLIENT, idClient)
    }

    override fun getIdClient() = appContext.readString(ID_CLIENT)

}