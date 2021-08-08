package com.example.giphyapp.gifGrid.adapter

import com.example.giphyapp.core.model.Gif
import javax.inject.Inject

class GridItemGifMapper @Inject constructor(){

    fun map(gifs: List<Gif>): List<GridItemGif> = gifs.map(::map)

    private fun map(gif: Gif): GridItemGif = with(gif){
        GridItemGif(id.hashCode(), id, smallUrl, title)
    }
}