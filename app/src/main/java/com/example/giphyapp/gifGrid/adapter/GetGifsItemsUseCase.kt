package com.example.giphyapp.gifGrid.adapter

import com.example.giphyapp.core.repository.SearchGiphyRepository
import com.example.giphyapp.core.repository.TrendingGiphyRepository
import io.reactivex.rxjava3.core.Flowable
import javax.inject.Inject

class GetGifsItemsUseCase @Inject constructor(
    private val trendingGiphyRepository: TrendingGiphyRepository,
    private val searchGiphyRepository: SearchGiphyRepository,
    private val gridItemGifMapper: GridItemGifMapper
) {

    private fun getItems() = trendingGiphyRepository.get()
        .mergeWith(searchGiphyRepository.get())
        .map(gridItemGifMapper::map)

    fun get(): Flowable<List<GridItemGif>> =
        getItems().map { items -> items.sortedBy { it.title } }

    fun getSortedDescending(): Flowable<List<GridItemGif>> =
        getItems().map { items -> items.sortedByDescending { it.title } }

}