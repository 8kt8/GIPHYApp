package com.example.giphyapp.core.repository

import androidx.annotation.WorkerThread
import com.example.giphyapp.core.model.Gif
import com.example.giphyapp.core.model.TrendingGifMapper
import com.example.giphyapp.core.schedulers.CoreSchedulers
import com.example.giphyapp.core.service.BackendConfig
import com.example.giphyapp.core.service.GiphyService
import io.reactivex.rxjava3.core.BackpressureStrategy
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.subjects.PublishSubject
import io.reactivex.rxjava3.subjects.Subject
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TrendingGiphyRepository @Inject constructor(
    private val giphyService: GiphyService,
    private val backendConfig: BackendConfig,
    private val trendingGifMapper: TrendingGifMapper,
    private val coreSchedulers: CoreSchedulers
){

    private val gifs: Subject<List<Gif>> =
        PublishSubject.create<List<Gif>>().toSerialized()

    @WorkerThread
    fun refresh(): Completable = giphyService.getTrending(backendConfig.apiKey)
        .map(trendingGifMapper::map)
        .doAfterSuccess(::updateTrendingGifs)
        .subscribeOn(coreSchedulers.networkIO)
        .ignoreElement()

    fun get(): Flowable<List<Gif>> = gifs.toFlowable(BackpressureStrategy.LATEST)

    private fun updateTrendingGifs(newItems: List<Gif>){
        gifs.onNext(newItems)
    }
}