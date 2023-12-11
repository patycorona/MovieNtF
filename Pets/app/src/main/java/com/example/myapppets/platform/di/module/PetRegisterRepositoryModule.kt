package com.example.myapppets.platform.di.module

import com.example.myapppets.data.network.CoreHomeApi
import com.example.myapppets.data.repository.PetRegisterRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class PetRegisterRepositoryModule {

    @Provides
    fun PetRegisterRepositoryProvider(apiService: CoreHomeApi): PetRegisterRepositoryImpl =
        PetRegisterRepositoryImpl(apiService)
}