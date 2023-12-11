package com.example.myapppets.platform.di.module

import com.example.myapppets.data.repository.PetRegisterRepositoryImpl
import com.example.myapppets.domian.usecase.PetRegisterUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class PetRegisterUseCaseModule {
    @Provides
    fun PetRegisterUseCaseProvider(petRegisterRepositoryImpl : PetRegisterRepositoryImpl) =
        PetRegisterUseCase(petRegisterRepositoryImpl)
}