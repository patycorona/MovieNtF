package com.example.myapppets.platform.di.module

import com.example.myapppets.data.repository.UserAccessRepositoryImpl
import com.example.myapppets.domian.usecase.UserAccessUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class AccessUseCaseRepositoryModule {
    @Provides
    fun UserAccessUseCaseProvider(userAccessRepositoryImpl : UserAccessRepositoryImpl) =
        UserAccessUseCase(userAccessRepositoryImpl)
}