package com.example.myapppets.platform.di.module

import com.example.myapppets.data.network.CoreHomeApi
import com.example.myapppets.data.repository.AllPetRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class AllPetRepositoryModule {
    @Provides
    fun AllPetRepositoryProvider(apiService: CoreHomeApi): AllPetRepositoryImpl = AllPetRepositoryImpl(apiService)
}