package com.example.giphyapp.core.serialization.di

import com.example.giphyapp.core.serialization.JsonAdapter
import com.example.giphyapp.core.serialization.MoshiAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class SerializationModule {

    @Binds
    internal abstract fun bindJsonAdapter(
        moshiAdapter: MoshiAdapter
    ): JsonAdapter

    companion object{
        @Provides
        @Singleton
        internal fun provideMoshi(): Moshi {
            return Moshi.Builder()
                .add(KotlinJsonAdapterFactory())
                .build()
        }
    }
}
