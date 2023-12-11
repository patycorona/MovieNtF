package com.example.myapppets.platform.di.module

import com.example.myapppets.data.repository.UserRegisterRepositoryImpl
import com.example.myapppets.domian.usecase.UserRegisterUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class UserRegisterUseCaseModule {
    @Provides
    fun UserRegisterUseCaseProvider(userRegisterRepositoryImpl : UserRegisterRepositoryImpl) =
        UserRegisterUseCase(userRegisterRepositoryImpl)
}