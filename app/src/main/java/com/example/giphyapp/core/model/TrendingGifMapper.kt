package com.example.giphyapp.core.model

import com.example.giphyapp.core.service.model.TrendingGifsDataResponse
import com.example.giphyapp.core.service.model.TrendingGifsResponse
import javax.inject.Inject

class TrendingGifMapper @Inject constructor() {

    fun map(response: TrendingGifsResponse): List<TrendingGif> = response.data.map(::map)

    private fun map(response: TrendingGifsDataResponse): TrendingGif = with(response){
        TrendingGif(id, images.original.url, images.small.url, title)
    }
}