package com.example.myapppets.platform.di.module
import com.example.myapppets.data.network.Constant
import com.example.myapppets.data.network.CoreHomeApi
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

var CONSTANT = 100

@InstallIn(SingletonComponent::class)
@Module
object RetrofitModule {
    var httpClient: OkHttpClient

    init {

        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        httpClient = OkHttpClient.Builder()
            .addInterceptor(interceptor) // .addInterceptor(REWRITE_CACHE_CONTROL_INTERCEPTOR)
            .connectTimeout(CONSTANT.toLong(), TimeUnit.SECONDS)
            .readTimeout(CONSTANT.toLong(), TimeUnit.SECONDS)
            .addNetworkInterceptor { chain ->
                val request: Request =
                    chain.request().newBuilder() // .addHeader(Constant.Header, authToken)
                        .build()
                chain.proceed(request)
            }.build()
    }

    @Provides
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(Constant.URL_BASE)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(httpClient)
            .build()

    @Provides
    fun provideCoreHomeApi(retrofit: Retrofit): CoreHomeApi {
        return retrofit.create(CoreHomeApi::class.java)
    }
}