package com.example.giphyapp.core.repository.useCase

import com.example.giphyapp.core.repository.TrendingGiphyRepository
import javax.inject.Inject

class RefreshTrendingGifsUseCase @Inject constructor(
    private val trendingGiphyRepository: TrendingGiphyRepository
) {

    fun refresh() = trendingGiphyRepository.refresh()
}