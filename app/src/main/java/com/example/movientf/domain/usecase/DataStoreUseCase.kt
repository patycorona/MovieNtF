package com.example.movientf.domain.usecase

import com.example.movientf.data.repository.DataStoreRepository
import javax.inject.Inject

class DataStoreUseCase @Inject constructor(
    var DataStoreRepository: DataStoreRepository
)  {

    suspend fun setToken(token: String) {
        DataStoreRepository.setToken(token)
    }

    fun getToken() = DataStoreRepository.getToken()

    suspend fun setName(name: String) {
        DataStoreRepository.setName(name)
    }

    fun getName() = DataStoreRepository.getName()
}