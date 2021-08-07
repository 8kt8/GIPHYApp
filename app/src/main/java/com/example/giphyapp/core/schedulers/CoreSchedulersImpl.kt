package com.example.giphyapp.core.schedulers

import io.reactivex.rxjava3.core.Scheduler
import javax.inject.Inject
import javax.inject.Qualifier
import javax.inject.Singleton

@Singleton
open class CoreSchedulersImpl
@Inject
constructor(
    @ComputationScheduler override val computation: Scheduler,
    @DbScheduler override val dbIO: Scheduler,
    @DiskIOScheduler override val diskIO: Scheduler,
    @NetworkScheduler override val networkIO: Scheduler,
    @MainThreadScheduler override val mainThread: Scheduler
): CoreSchedulers

@Qualifier
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
annotation class ComputationScheduler

@Qualifier
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
annotation class DbScheduler

@Qualifier
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
annotation class DiskIOScheduler

@Qualifier
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
annotation class NetworkScheduler

@Qualifier
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
annotation class MainThreadScheduler