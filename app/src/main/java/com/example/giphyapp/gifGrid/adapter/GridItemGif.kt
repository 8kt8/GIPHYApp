package com.example.giphyapp.gifGrid.adapter

import com.example.giphyapp.R
import com.example.giphyapp.common.ui.ListItem

data class GridItemGif(
    override val id: Int,
    val gifId: String,
    val gifUrl: String,
    val title: String
): ListItem {

    override val type: Int = R.id.grid_gif_item

}