package com.example.myapppets.platform.di.component

import com.example.myapppets.platform.di.module.*
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AccessUseCaseRepositoryModule::class,
        AllPetRepositoryModule::class,
        AllPetUseCaseModule::class,
        PetRegisterRepositoryModule::class,
        PetRegisterUseCaseModule::class,
        RegisterRepositoryModule::class,
        RepositoryModule::class,
        RetrofitModule::class,
        UserRegisterUseCaseModule ::class
    ]
)
interface MainComponent