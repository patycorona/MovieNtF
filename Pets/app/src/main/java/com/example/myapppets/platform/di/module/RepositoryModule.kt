package com.example.myapppets.platform.di.module

import com.example.myapppets.data.repository.UserAccessRepositoryImpl
import com.example.myapppets.data.network.CoreHomeApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class RepositoryModule {

    @Provides
    fun UserAccessRepositoryProvider(apiService: CoreHomeApi): UserAccessRepositoryImpl = UserAccessRepositoryImpl(apiService)
}