package com.example.giphyapp.gifGrid.adapter

import com.example.giphyapp.core.repository.TrendingGiphyRepository
import javax.inject.Inject

class GetGifsItemsUseCase @Inject constructor(
    private val trendingGiphyRepository: TrendingGiphyRepository,
    private val gridItemGifMapper: GridItemGifMapper
) {

    fun get() = trendingGiphyRepository.get()
        .map(gridItemGifMapper::map)
}