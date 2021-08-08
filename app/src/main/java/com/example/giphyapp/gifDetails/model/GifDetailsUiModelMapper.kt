package com.example.giphyapp.gifDetails.model

import com.example.giphyapp.common.utils.exhaustive
import com.example.giphyapp.core.model.Gif
import com.example.giphyapp.core.model.GifRatingType
import javax.inject.Inject

class GifDetailsUiModelMapper @Inject constructor() {

    fun map(gif: Gif) = with(gif){
        GifDetailsUiModel(
            url = pageUrl,
            girUrl = originalUrl,
            title = getTitle(title),
            source = getSource(source),
            rating = getGifRatingDescription(ratingCode)
        )
    }

    private fun getTitle(title: String) =
       if(title.isBlank()) "-" else title

    private fun getSource(source: String) =
        if(source.isBlank()) "-" else source

    private fun getGifRatingDescription(ratingCode: String) =
        when(GifRatingType.fromCode(ratingCode)){
            GifRatingType.G -> "G Level 1"
            GifRatingType.PG -> "PG Level 2"
            GifRatingType.PG13 -> "PG-13 Level 3"
            GifRatingType.R -> "R Level 4"
            GifRatingType.UNKNOWN -> "-"
        }.exhaustive
}