package com.example.movientf.platform.di.module

import com.example.movientf.data.repository.DataStoreRepositoryImpl
import com.example.movientf.data.repository.LoginRepositoryImpl
import com.example.movientf.domain.usecase.DataStoreUseCase
import com.example.movientf.domain.usecase.LoginUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class UseCaseModule {

    @Provides
    fun loginUseCaseProvider(loginRepositoryImpl : LoginRepositoryImpl) =
        LoginUseCase(loginRepositoryImpl)

    @Provides
    fun dataStoreUseCaseProvider(dataStoreRepositoryImpl: DataStoreRepositoryImpl) =
        DataStoreUseCase(dataStoreRepositoryImpl)
}