package com.example.giphyapp.core.repository.useCase

import com.example.giphyapp.core.repository.TrendingGiphyRepository
import javax.inject.Inject

class GetTrendingGifsUseCase @Inject constructor(
    private val trendingGiphyRepository: TrendingGiphyRepository
) {

    fun get() = trendingGiphyRepository.get()
}