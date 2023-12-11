package com.example.movientf.platform.di.component

import com.example.movientf.platform.di.module.RepositoryModule
import com.example.movientf.platform.di.module.RetrofitModule
import com.example.movientf.platform.di.module.UseCaseModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        RepositoryModule::class,
        RetrofitModule::class,
        UseCaseModule::class
    ]
)
interface MainComponent