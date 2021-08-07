package com.example.giphyapp.core.di

import com.example.giphyapp.core.service.BackendConfig
import com.example.giphyapp.core.service.GiphyService
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    internal fun provideMoshiConverterFactory(moshi: Moshi): MoshiConverterFactory =
        MoshiConverterFactory.create(moshi)

    @Provides
    @Singleton
    internal fun provideRxJava3CallAdapterFactory(): RxJava3CallAdapterFactory =
        RxJava3CallAdapterFactory.create()

    @Provides
    @Singleton
    internal fun provideConfig(): BackendConfig = BackendConfig()

    @Provides
    @Singleton
    internal fun provideRetrofitBuilder(
        backendConfig: BackendConfig,
        httpClient: OkHttpClient,
        moshiConverterFactory: MoshiConverterFactory,
        rxJavaCallAdapterFactory: RxJava3CallAdapterFactory
    ): GiphyService =
        Retrofit.Builder()
            .baseUrl(backendConfig.apiUrl)
            .client(httpClient)
            .addConverterFactory(moshiConverterFactory)
            .addCallAdapterFactory(rxJavaCallAdapterFactory)
            .build()
            .create(GiphyService::class.java)
}