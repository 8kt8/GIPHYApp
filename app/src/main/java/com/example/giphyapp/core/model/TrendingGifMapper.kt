package com.example.giphyapp.core.model

import com.example.giphyapp.core.service.model.GifsDataResponse
import com.example.giphyapp.core.service.model.GifsListResponse
import com.example.giphyapp.core.service.model.GifsResponse
import javax.inject.Inject

class TrendingGifMapper @Inject constructor() {

    fun map(listResponse: GifsListResponse): List<Gif> = listResponse.data.map(::map)

    fun map(response: GifsDataResponse): Gif = with(response){
        Gif(
            id = id,
            originalUrl = images.original.url,
            smallUrl = images.small.url,
            title = title,
            source = source,
            ratingCode = ratingCode,
            pageUrl = url
        )
    }

    fun map(response: GifsResponse): Gif = map(response.data)
}