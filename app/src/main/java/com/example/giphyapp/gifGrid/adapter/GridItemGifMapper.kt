package com.example.giphyapp.gifGrid.adapter

import com.example.giphyapp.core.model.TrendingGif
import javax.inject.Inject

class GridItemGifMapper @Inject constructor(){

    fun map(trendingGifs: List<TrendingGif>): List<GridItemGif> = trendingGifs.map(::map)

    private fun map(trendingGif: TrendingGif): GridItemGif = with(trendingGif){
        GridItemGif(id.hashCode(), smallUrl, title)
    }
}