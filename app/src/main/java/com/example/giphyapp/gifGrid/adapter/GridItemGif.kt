package com.example.giphyapp.gifGrid.adapter

import com.example.giphyapp.common.ui.ListItem

data class GridItemGif(
    override val id: Int,
    val gifUrl: String,
    val title: String
): ListItem