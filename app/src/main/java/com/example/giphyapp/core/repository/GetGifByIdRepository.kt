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
class GetGifByIdRepository @Inject constructor(
    private val giphyService: GiphyService,
    private val backendConfig: BackendConfig,
    private val trendingGifMapper: TrendingGifMapper,
    private val coreSchedulers: CoreSchedulers
){

    private val searchResult: Subject<Gif> =
        PublishSubject.create<Gif>().toSerialized()

    @WorkerThread
    fun refreshById(id: String): Completable = giphyService.getById(id, backendConfig.apiKey)
        .map(trendingGifMapper::map)
        .doAfterSuccess(::updateTrendingGif)
        .subscribeOn(coreSchedulers.networkIO)
        .ignoreElement()

    fun getById(id: String): Flowable<Gif> = searchResult.toFlowable(BackpressureStrategy.LATEST)
        .filter { it.id == id }

    private fun updateTrendingGif(newItem: Gif){
        searchResult.onNext(newItem)
    }
}