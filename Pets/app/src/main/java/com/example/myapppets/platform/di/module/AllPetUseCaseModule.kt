package com.example.myapppets.platform.di.module

import com.example.myapppets.data.repository.AllPetRepositoryImpl
import com.example.myapppets.domian.usecase.AllPetUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class AllPetUseCaseModule {
    @Provides
    fun AllPetUseCaseProvider(allPetRepositoryImpl : AllPetRepositoryImpl) =
        AllPetUseCase(allPetRepositoryImpl)
}