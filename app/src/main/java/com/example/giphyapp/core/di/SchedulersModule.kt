package com.example.giphyapp.core.di

import com.example.giphyapp.core.schedulers.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SchedulersModule {

    @Provides
    @DbScheduler
    internal fun provideDbScheduler(): Scheduler {
        return Schedulers.single()
    }

    @Provides
    @NetworkScheduler
    internal fun provideNetworkScheduler(): Scheduler {
        return Schedulers.io()
    }

    @Provides
    @ComputationScheduler
    internal fun provideComputationScheduler(): Scheduler {
        return Schedulers.computation()
    }

    @Provides
    @DiskIOScheduler
    internal fun provideDiskIoScheduler(): Scheduler {
        return Schedulers.io()
    }

    @Provides
    @MainThreadScheduler
    internal fun provideMainThreadScheduler(): Scheduler {
        return AndroidSchedulers.mainThread()
    }

    @Provides
    @Singleton
    internal fun provideCoreSchedulers(
        @ComputationScheduler computation: Scheduler,
        @DbScheduler dbIO: Scheduler,
        @DiskIOScheduler diskIO: Scheduler,
        @NetworkScheduler networkIO: Scheduler,
        @MainThreadScheduler mainThread: Scheduler
    ): CoreSchedulers {
        return CoreSchedulersImpl(computation, dbIO, diskIO, networkIO, mainThread)
    }

}