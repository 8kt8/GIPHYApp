package com.example.giphyapp.core.repository

import com.example.giphyapp.core.schedulers.CoreSchedulers
import com.example.giphyapp.core.service.BackendConfig
import com.example.giphyapp.core.service.GiphyService
import javax.inject.Inject

class GiphyRepository @Inject constructor(
    private val giphyService: GiphyService,
    private val backendConfig: BackendConfig,
    private val coreSchedulers: CoreSchedulers
){

    fun getTrendingGifs() = giphyService.getTrending(backendConfig.apiKey)
        .subscribeOn(coreSchedulers.networkIO)
}