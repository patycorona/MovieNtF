package com.example.myfavoritewebsites.platform.component

import com.example.myfavoritewebsites.platform.module.RepositoryModule
import com.example.myfavoritewebsites.platform.module.RetrofitModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        RepositoryModule::class,
        RetrofitModule::class
    ]
)
interface MainComponent
