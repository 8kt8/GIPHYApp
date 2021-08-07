package com.example.giphyapp.core.model

import com.example.giphyapp.core.service.model.GifsDataResponse
import com.example.giphyapp.core.service.model.GifsResponse
import javax.inject.Inject

class TrendingGifMapper @Inject constructor() {

    fun map(response: GifsResponse): List<TrendingGif> = response.data.map(::map)

    private fun map(response: GifsDataResponse): TrendingGif = with(response){
        TrendingGif(id, images.original.url, images.small.url, title)
    }
}