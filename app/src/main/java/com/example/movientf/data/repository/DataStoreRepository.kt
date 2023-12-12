package com.example.movientf.data.repository

import kotlinx.coroutines.flow.Flow

interface DataStoreRepository {

    suspend fun setToken(token :String)  fun getToken(): Flow<String>

    suspend fun setName(name: String) fun getName(): Flow<String>
}