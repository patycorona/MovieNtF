package com.example.movientf.platform.di.module

import com.example.movientf.data.repository.DataStoreRepositoryImpl
import com.example.movientf.data.repository.LoginRepositoryImpl
import com.example.movientf.data.repository.MockCoreServiceApi
import com.example.movientf.data.repository.ProfileRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class RepositoryModule {

    @Provides
    fun loginRepositoryProvider(mockCoreServiceApi: MockCoreServiceApi): LoginRepositoryImpl = LoginRepositoryImpl(mockCoreServiceApi)

    @Provides
    fun dataStoreRepositoryProvider(): DataStoreRepositoryImpl = DataStoreRepositoryImpl()

    @Provides
    fun mockRepositoryProvider(): MockCoreServiceApi = MockCoreServiceApi()

    @Provides
    fun profileRepositoryProvider(mockCoreServiceApi : MockCoreServiceApi): ProfileRepositoryImpl = ProfileRepositoryImpl(mockCoreServiceApi)
}